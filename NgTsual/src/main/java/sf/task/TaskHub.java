package sf.task;

import sf.uds.interfaces.del.IExec_0;
import sf.uds.interfaces.del.IRun_1;
import sf.uds.tree.NodeTreeHub;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskHub {
    private final static int report_cache_size = 10;
    private final static int report_cache_size_m1 = report_cache_size - 1;

    private TaskHost host;
    private List<Task> tasks = new ArrayList<>();
    private IRun_1<String> traceShell;

    final Object wait_lock = "(*˘︶˘*).。.:*♡";
    boolean anyFinish = false;
    volatile int finish_count = 0;
    private int index = 0;
    private final Long allow_delay;
    private final Long[] delays = new Long[report_cache_size_m1];
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
        if (!anyFinish)
            anyFinish = true;
        long all = task.finishTime - task.startTime;
        synchronized (delays) {
            if (index < report_cache_size_m1)
                delays[index++] = all;
            else {
                index = 0;
                for (int i = 0; i < report_cache_size_m1; i++) {
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

    public <T> Task<T> execute(IExec_0<T> executable, ThreadLocalOperation threadLocalOperation) {
        Task<T> task = new Task<>(this, executable);
        if (threadLocalOperation != null)
            task.tlOperation = threadLocalOperation;
        tasks.add(task);
        host.addTask(task);
        return task;
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
                        host.abort_task(task, hub);
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

    public void waitAll() {
        int size = tasks.size();
        while (true) {
            if (finish_count >= size) return;
            synchronized (wait_lock) {
                try {
                    wait_lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public String trackWorker() throws Exception {
        NodeTreeHub trees = new NodeTreeHub();
        for (Task task : tasks)
            if (!task.isProduced())
                trees.push(task.caller, task.executor);
        return trees.toJson();
    }
}
