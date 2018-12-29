package sf.task;


import sf.uds.interfaces.del.IRun_1;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.LinkedList;
import java.util.Queue;

public class TaskHost implements AutoCloseable {
    private final String name;
    private final ThreadGroup thread_group;
    private final Integer max_worker_count;
    private final SimpleTaskQueue simpleTaskQueue = new SimpleTaskQueue();

    volatile boolean is_add_daemon_alive = true;
    final String add_daemon_lock = "ヾ(^▽^*)))";
    private final String host_lock = "(இωஇ )";
    private final String workers_lock = "（▼へ▼メ）";
    private final boolean[] thread_close = {false};


    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (t, e) -> start_worker();

    private final Runnable worker_exec_shell = () ->
    {
        while (!thread_close[0]) {
            Task task = null;
            try {
                synchronized (host_lock) {
                    if (simpleTaskQueue.remain()) {
                        task = simpleTaskQueue.get();
                        host_lock.notify();
                    } else {
                        host_lock.wait();
                        if (thread_close[0]) break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (task == null) continue;
            final TaskWorker curThread = (TaskWorker) Thread.currentThread();

            switch (task.tlOperation) {
                case Copy:
                    copyThreadLocal(task.caller);
                    break;
                case None:
                    break;
                case Reset:
                    resetThreadLocal();
                    break;
            }

            task.executeTime = System.currentTimeMillis();
            task.executor = curThread;
            task.status = TaskStatus.Executing;
            if (task.hub.needTrace())
                task.hub.trace(task, task.executor.getName() + "<<Begin executing");

            try {
                curThread.task = task;
                task.produceResult = task.executable.execute();
                task.status = TaskStatus.Finished;
                if (task.hub.needTrace())
                    task.hub.trace(task, "Finish executing,result:" + task.produceResult);
                task.finishTask();
                task.notifyFinish();
            } catch (InterruptedException ignored) {
            } catch (Exception ex) {
                task.produceException = ex;
                task.status = TaskStatus.Error;
                if (task.hub.needTrace())
                    task.hub.trace(task, "Caught Exception:" + ex.toString());
                task.finishTask();
                task.notifyFinish();
            } finally {
                curThread.task = null;
            }
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

    void abort_task(Task task, TaskHub hub) {
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
            simpleTaskQueue.remove(task);
        }

        task.caller = null;
        task.executor = null;
        task.notifyFinish();
    }

    public TaskHost(String name, Integer start_worker_count, Integer max_worker_count, Long allow_wait_time) {
        if (start_worker_count < 1 || max_worker_count <= start_worker_count) {
            start_worker_count = 5;
            max_worker_count = 25;
        }
        this.simpleTaskQueue.ptx_time = allow_wait_time > 50 ? allow_wait_time : 200;
        this.simpleTaskQueue.host = this;
        this.name = name;
        this.thread_group = new ThreadGroup("TaskHost-" + name);
        this.max_worker_count = max_worker_count;
        while (start_worker_count-- > 0)
            start_worker();

        TaskWorkerDaemonHub daemons = new TaskWorkerDaemonHub();
        daemons.start_add_daemon();
    }

    private void start_worker() {
        synchronized (workers_lock) {
            final Thread worker = new TaskWorker(thread_group, worker_exec_shell, thread_group.getName() + ":TaskWorker@" + thread_group.activeCount());
            worker.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            worker.setPriority(8);
            worker.start();
        }
    }

    void addTask(Task task) {
        simpleTaskQueue.add(task);
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

    private class SimpleTaskQueue {
        private Queue<Task> queue_0 = new LinkedList<>();
        private Queue<Task> queue_1 = new LinkedList<>();

        private final String lock_queue_1 = "(*^ω^*)";

        Long ptx_time;
        TaskHost host;

        Task get() {
            synchronized (this) {
                return queue_0.poll();
            }
        }

        void remove(Task task) {
            synchronized (queue_1) {
                if (queue_1.contains(task))
                    queue_1.remove(task);
                else {
                    synchronized (this) {
                        queue_0.remove(task);
                    }
                }
            }
        }

        void add(Task task) {
            synchronized (lock_queue_1) {
                queue_1.offer(task);
            }
        }

        boolean remain() {
            synchronized (this) {
                if (queue_0.size() > 0)
                    return true;
                else {
                    synchronized (lock_queue_1) {
                        if (queue_1.size() == 0) return false;
                        if (System.currentTimeMillis() - queue_1.element().startTime > ptx_time)
                            synchronized (host.add_daemon_lock) {
                                host.add_daemon_lock.notify();
                            }
                        Queue<Task> queue = queue_0;
                        queue_0 = queue_1;
                        queue_1 = queue;
                        return true;
                    }
                }
            }
        }
    }

    /**
     * 两个灾难性问题
     * 1.线程自引用或递归引用导致的资源枯竭
     * 2.调用线程状态异常而处理线程仍工作的问题导致的资源枯竭
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
    }

}
