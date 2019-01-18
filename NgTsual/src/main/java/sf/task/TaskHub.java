package sf.task;

import sf.uds.del.IExec_0;
import sf.uds.del.IRun_1;
import sf.util.NodeTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskHub {
    private final static int report_cache_size = 10;

    TaskHost host;
    private List<Task> tasks = new ArrayList<>();
    private IRun_1<String> traceShell;

    final Object wait_lock = "(*˘︶˘*).。.:*♡";
    private int index = 0;
    private final Long allow_delay;
    private final Long[] delays = new Long[report_cache_size - 1];
    private Timer abort_schedule;


    TaskHub(TaskHost host, Long allow_delay, IRun_1<String> traceShell) {
        this.host = host;
        this.allow_delay = allow_delay * report_cache_size;
        this.traceShell = traceShell;
    }

    boolean needTrace() {
        return traceShell != null;
    }

    void trace(Task task, String msg) {
        try {
            if (traceShell != null)
                traceShell.run("(" + task.toString() + ") ASYNC_TRACE " + msg + "##" + task.getTraceInfo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void taskFinishReport(Task task) {
        long all = task.finishTime - task.startTime;
        synchronized (delays) {
            if (index < report_cache_size - 1)
                delays[index++] = all;
            else {
                index = 0;
                for (int i = 0; i < report_cache_size - 1; i++) {
                    all += delays[i];
                    delays[i] = 0L;
                }
            }
        }
        if (all > allow_delay && host.is_add_daemon_alive)
            synchronized (host.add_daemon_lock) {
                host.add_daemon_lock.notifyAll();
            }
    }

    public <T> Task<T> execute(IExec_0<T> executable) {
        return execute(executable, ThreadLocalOperation.None, 0L);
    }

    public <T> Task<T> execute(IExec_0<T> executable, ThreadLocalOperation threadLocalOperation) {
        return execute(executable, threadLocalOperation, 0L);
    }

    public <T> Task<T> execute(IExec_0<T> executable, ThreadLocalOperation threadLocalOperation, Long abortDuration) {
        final Task<T> task = new Task<>(this, executable);
        if (threadLocalOperation != null)
            task.tlOperation = threadLocalOperation;
        if (10 < abortDuration) {
            if (abort_schedule == null)
                abort_schedule = new Timer();
            task.abortDuration = abortDuration;
            final TaskHub hub = this;
            abort_schedule.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        host.abort_task_overtime(task, hub);
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }, abortDuration);
        }
        tasks.add(task);
        host.addTask(task);
        return task;
    }

    private boolean nAllFinish(int size) {
        while (size-- > 0)
            if (!tasks.get(size).isProduced()) return true;
        return false;
    }

    public void waitAll() {
        int size = tasks.size();
        while (nAllFinish(size)) {
            synchronized (wait_lock) {
                try {
                    wait_lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void waitAll(long ms) {
        final long sl = System.currentTimeMillis();
        int size = tasks.size();
        while (nAllFinish(size) || (ms - System.currentTimeMillis() + sl > 0))
            ms_wait(ms, sl);
    }

    private void ms_wait(long ms, final long sl) {
        synchronized (wait_lock) {
            final long kl = ms - System.currentTimeMillis() + sl;
            try {
                if (kl > 0) wait_lock.wait(kl);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void waitJump(long ms) {
        final long sl = System.currentTimeMillis();
        int size = tasks.size();
        while (nAllFinish(size)) {
            ms_wait(ms, sl);
            if (ms - System.currentTimeMillis() + sl < 0) {
                for (Task task : tasks)
                    if (!task.isProduced())
                        task.execute();
                break;
            }
        }
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public String trackWorker() throws Exception {
        NodeTree.Hub trees = new NodeTree.Hub();
        for (Task task : tasks)
            if (!task.isProduced())
                trees.push(task.caller, task.executor);
        return trees.toJson();
    }
}
