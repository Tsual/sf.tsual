package sf.task;


import sf.uds.del.IRun_1;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.UUID;

public class TaskHost implements AutoCloseable {
    final String name;
    final ThreadGroup thread_group;
    final Integer max_worker_count;
    ITaskQueue<Task> task_queue;

    volatile boolean is_add_daemon_alive = true;
    final String add_daemon_lock = "ヾ(^▽^*)))";
    private final String host_lock = "(இωஇ )";
    private volatile boolean thread_close = false;
    private final TaskWorkerDaemonHub daemons = new TaskWorkerDaemonHub();
    private final Util u = new Util();

    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (t, e) -> start_worker();

    private final Runnable worker_exec_shell = () ->
    {
        TaskWorker tw_ptr = (TaskWorker) Thread.currentThread();
        while (!thread_close) {
            Task task = tw_ptr.task;
            if (task != null) try {
                synchronized (host_lock) {
                    if ((task = task_queue.next()) != null) {
                        host_lock.notifyAll();
                    } else {
                        tw_ptr.state = TaskWorker.State.WAITING;
                        host_lock.wait();
                        if (thread_close) {
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

            //如果由其他线程执行task，就跳过这个task
            if (task == null) continue;

            //对线程变量的操作
            switch (task.tlOperation) {
                case Reset:
                    u.resetThreadLocal();
                    break;
                case Copy:
                    u.copyThreadLocal(task.caller);
                    break;
            }

            tw_ptr.task = task;
            task.execute();
            tw_ptr.task = null;
        }
        tw_ptr.state = TaskWorker.State.QUIT;
    };

    void abort_task_overtime(Task task, TaskHub hub) {
        if (task.isProduced()) return;
        if (task.executor instanceof TaskWorker) u.resetWorker(task.executor, this);
        else task.isAbort = true;
        task.lifecycle.overtime();
        task.notifyFinish();
    }

    public TaskHost() {
        final UUID uuid = UUID.randomUUID();
        int start_worker_count = 5;
        this.task_queue = new AdvanceTaskQueue(this, 200L);
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
        this.task_queue = new AdvanceTaskQueue(this, allow_wait_time > 50 ? allow_wait_time : 200);
        this.name = name;
        this.thread_group = new ThreadGroup("TaskHost-" + name);
        this.max_worker_count = max_worker_count;
        while (start_worker_count-- > 0)
            start_worker();
        daemons.start_add_daemon();
    }

    void addTask(Task task) {
        task_queue.add(task);
        task.status = Task.TaskStatus.Queueing;
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
        thread_close = true;
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

    private void start_worker() {
        final Thread worker = new TaskWorker(this.thread_group, this.worker_exec_shell, this.thread_group.getName() + ":TaskWorker@" + this.thread_group.activeCount());
        worker.setUncaughtExceptionHandler(this.uncaughtExceptionHandler);
        worker.setPriority(5);
        worker.start();
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
                while (!thread_close) {
                    try {
                        synchronized (add_daemon_lock) {
                            add_daemon_lock.wait();
                            if (thread_close) return;
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

    private class Util {
        private void resetWorker(final Thread worker, TaskHost taskHost) {
            final Thread thread = new TaskWorker(taskHost.thread_group, taskHost.worker_exec_shell, worker.getName());
            thread.setUncaughtExceptionHandler(taskHost.uncaughtExceptionHandler);
            thread.setPriority(worker.getPriority());
            thread.start();
            AccessController.doPrivileged((PrivilegedAction) () ->
            {
                try {
                    worker.interrupt();
                } catch (Throwable e) {
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
    }
}
