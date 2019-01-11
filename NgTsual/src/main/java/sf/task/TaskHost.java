package sf.task;


import sf.uds.del.IRun_1;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.UUID;

public class TaskHost implements AutoCloseable {
    private final String name;
    private final ThreadGroup thread_group;
    private final Integer max_worker_count;
    final AbsTaskQueue task_queue = new SimpleTaskQueue();

    volatile boolean is_add_daemon_alive = true;
    final String add_daemon_lock = "ヾ(^▽^*)))";
    private final String host_lock = "(இωஇ )";
    private final String workers_lock = "（▼へ▼メ）";
    private final boolean[] thread_close = {false};
    private final TaskWorkerDaemonHub daemons = new TaskWorkerDaemonHub();

    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (t, e) -> start_worker();

    private final Runnable worker_exec_shell = () ->
    {
        TaskWorker tw_ptr = (TaskWorker) Thread.currentThread();
        while (!thread_close[0]) {
            Task task = null;
            try {
                synchronized (host_lock) {
                    if (task_queue.remain()) {
                        task = task_queue.get();
                        host_lock.notify();
                    } else {
                        tw_ptr.state = TaskWorker.State.WAITING;
                        host_lock.wait();
                        if (thread_close[0]) {
                            tw_ptr.state = TaskWorker.State.QUIT;
                            break;
                        } else {
                            tw_ptr.state = TaskWorker.State.WORKING;
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (task == null) continue;
            synchronized (task.exec_lock) {
                if (task.isProduced) continue;
                switch (task.tlOperation) {
                    case None:
                        break;
                    case Reset:
                        resetThreadLocal();
                        break;
                    case Copy:
                        copyThreadLocal(task.caller);
                        break;
                }
                task.executeTime = System.currentTimeMillis();
                task.executor = tw_ptr;
                task.status = TaskStatus.Executing;
                if (task.hub.needTrace())
                    task.hub.trace(task, task.executor.getName() + "<<Begin executing");
                try {
                    tw_ptr.task = task;
                    task.produceResult = task.executable.execute();
                    task.status = TaskStatus.Finished;
                } catch (InterruptedException ignored) {
                } catch (Exception ex) {
                    task.produceException = ex;
                    task.status = TaskStatus.Error;
                } finally {
                    task.finishTask();
                    task.notifyFinish();
                    tw_ptr.task = null;
                }
            }
            tw_ptr.state = TaskWorker.State.QUIT;
        }
    };


    private void resetWorker(final Thread worker) {
        final Thread thread = new TaskWorker(thread_group, worker_exec_shell, worker.getName());
        thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        thread.setPriority(worker.getPriority());
        thread.start();
        AccessController.doPrivileged((PrivilegedAction) () ->
        {
            try {
                worker.interrupt();
            } catch (Throwable e) {
                //throw new RuntimeException(e);
                worker.stop();
                return null;
            }
            return null;
        });
    }

    private void copyThreadLocal(final Thread caller) {
        AccessController.doPrivileged((PrivilegedAction) () ->
        {
            try {
                Field threadLocals = Thread.class.getDeclaredField("threadLocals");
                threadLocals.setAccessible(true);
                threadLocals.set(Thread.currentThread(), threadLocals.get(caller));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return null;
        });
    }

    private void resetThreadLocal() {
        AccessController.doPrivileged((PrivilegedAction) () ->
        {
            try {
                Field threadLocals = Thread.class.getDeclaredField("threadLocals");
                threadLocals.setAccessible(true);
                threadLocals.set(Thread.currentThread(), null);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return null;
        });
    }

    void abort_task_overtime(Task task, TaskHub hub) {
        if (task.isProduced)
            return;

        task.status = TaskStatus.Overtime;
        if (task.hub.needTrace())
            task.hub.trace(task, "Task overtime:" + task.abortDuration);
        task.isProduced = true;
        task.finishTime = System.currentTimeMillis();
        if (!hub.anyFinish)
            hub.anyFinish = true;

        if (task.executor != null) {
            resetWorker(task.executor);
        } else {
            task_queue.remove(task);
        }

        task.caller = null;
        task.executor = null;
        task.notifyFinish();
    }

    public TaskHost() {
        final UUID uuid = UUID.randomUUID();
        int start_worker_count = 5;
        this.task_queue.remind_host_time = 200L;
        this.task_queue.host = this;
        this.name = uuid.toString();
        this.thread_group = new ThreadGroup("TaskHost-" + name);
        this.max_worker_count = 20;
        while (start_worker_count-- > 0)
            start_worker();
        daemons.start_add_daemon();
    }

    public TaskHost(String name, Integer start_worker_count, Integer max_worker_count, Long allow_wait_time) {
        if (start_worker_count < 1 || max_worker_count <= start_worker_count) {
            start_worker_count = 5;
            max_worker_count = 25;
        }
        this.task_queue.remind_host_time = allow_wait_time > 50 ? allow_wait_time : 200;
        this.task_queue.host = this;
        this.name = name;
        this.thread_group = new ThreadGroup("TaskHost-" + name);
        this.max_worker_count = max_worker_count;
        while (start_worker_count-- > 0)
            start_worker();
        daemons.start_add_daemon();
    }

    private void start_worker() {
        synchronized (workers_lock) {
            final Thread worker = new TaskWorker(thread_group, worker_exec_shell, thread_group.getName() + ":TaskWorker@" + thread_group.activeCount());
            worker.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            worker.setPriority(5);
            worker.start();
        }
    }

    void addTask(Task task) {
        task_queue.add(task);
        task.status = TaskStatus.Queueing;
        if (task.hub.needTrace())
            task.hub.trace(task, "Begin Queueing");
        synchronized (host_lock) {
            host_lock.notify();
        }
    }

    public TaskHub newTaskHub(Long allow_delay, IRun_1<String> traceShell) {
        return new TaskHub(this, allow_delay, traceShell);
    }

    @Override
    public void close() {
        thread_close[0] = true;
        synchronized (host_lock) {
            host_lock.notifyAll();
        }
        synchronized (add_daemon_lock) {
            add_daemon_lock.notifyAll();
        }
    }

    public String getName() {
        return name;
    }

    /**
     * 两个灾难性问题
     * half solved 1.线程自引用或递归引用导致的资源枯竭
     * half solved 2.调用线程状态异常而处理线程仍工作的问题导致的资源枯竭
     */
    private class TaskWorkerDaemonHub {
        private ThreadGroup threadGroup = new ThreadGroup(name + "-:Daemon");

        void start_add_daemon() {
            final Thread thread = new Thread(threadGroup, () ->
            {
                while (true) {
                    if (thread_close[0]) return;
                    try {
                        synchronized (add_daemon_lock) {
                            add_daemon_lock.wait();
                            if (thread_close[0]) return;
                            if (thread_group.activeCount() < max_worker_count)
                                start_worker();
                            else {
                                is_add_daemon_alive = false;
                                return;
                            }
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();
        }

        boolean check_all_worker_busy() {
            Thread[] thg = new Thread[threadGroup.activeCount()];
            threadGroup.enumerate(thg);
            for (Thread thw : thg) {
                if (thw instanceof TaskWorker)
                    if (((TaskWorker) thw).state == TaskWorker.State.WAITING)
                        return false;
            }
            return true;
        }
    }

}
