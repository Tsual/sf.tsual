package sf.task;

import sf.uds.del.IExec_0;
import sf.uds.del.IRun_0;

public class Task<T> {
    TaskHub hub;
    private volatile boolean isProduced = false;
    volatile boolean isInProducing = false;
    volatile boolean isAbort = false;
    Long startTime, executeTime, finishTime, abortDuration;
    Task<T> next;

    private IExec_0<T> executable;
    private T produceResult = null;
    private Exception produceException = null;
    Thread caller, executor;
    TaskStatus status = TaskStatus.Created;
    ThreadLocalOperation tlOperation = ThreadLocalOperation.None;
    private final Object sf_lock = "($_$)";
    final TaskLifecycle lifecycle = new TaskLifecycle(this);

    void notifyFinish() {
        synchronized (hub.wait_lock) {
            hub.wait_lock.notifyAll();
        }
        synchronized (sf_lock) {
            sf_lock.notifyAll();
        }
    }

    Task(TaskHub hub, IExec_0<T> executable) {
        this.hub = hub;
        this.executable = executable;
        this.startTime = System.currentTimeMillis();
        this.caller = Thread.currentThread();
    }

    public T awaitResult() throws Exception {
        await();
        return getResult();
    }

    public T awaitResult(int second) throws Exception {
        await(second);
        return getResult();
    }

    public T awaitResultClose() throws Exception {
        await();
        if (hub != null) hub.getTasks().remove(this);
        return getResult();
    }

    public T awaitResultClose(int second) throws Exception {
        await(second);
        if (hub != null) hub.getTasks().remove(this);
        return getResult();
    }

    public T getResult() throws Exception {
        if (produceException != null) throw produceException;
        return produceResult;
    }

    public void await() {
        if (!isProduced) synchronized (sf_lock) {
            try {
                if (!isProduced) sf_lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void await(long second) {
        if (!isProduced) synchronized (sf_lock) {
            try {
                if (!isProduced)
                    sf_lock.wait(second);
                if (!isProduced)
                    execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public boolean isProduced() {
        return isProduced;
    }

    Task<T> execute() {
        if (isProduced ^ isAbort ^ isInProducing) {
            isInProducing = true;
            lifecycle.startExecuting();
            try {
                produceResult = executable.execute();
            } catch (InterruptedException ignored) {
            } catch (Exception ex) {
                lifecycle.crashException(ex);
            } finally {
                notifyFinish();
            }
            lifecycle.finish();
        }
        return next == null ? this : next.execute();
    }

    public T syncExecute() throws Exception {
        return execute().getResult();
    }

    @Override
    public String toString() {
        return "AsyncTask-" + hashCode();
    }

    String getTraceInfo() {
        return "Task{" +
                "isProduced=" + isProduced +
                ", startTime=" + startTime +
                ", executeTime=" + executeTime +
                ", finishTime=" + finishTime +
                ", abortDuration=" + abortDuration +
                ", produceResult=" + produceResult +
                ", produceException=" + produceException +
                '}';
    }

    public enum TaskStatus {
        Created,
        Queueing,
        Executing,
        Finished,
        Overtime,
        Error,
    }

    class TaskLifecycle {
        Task task;

        TaskLifecycle(Task task) {
            this.task = task;
        }

        void startExecuting() {
            status = TaskStatus.Executing;
            executeTime = System.currentTimeMillis();
            executor = Thread.currentThread();
            if (hub.needTrace()) hub.trace(task, executor.getName() + "<<Begin executing");
        }

        void crashException(Exception ex) {
            produceException = ex;
            status = TaskStatus.Error;
            isProduced = true;
            finishTime = System.currentTimeMillis();
            hub.taskFinishReport(task);
            caller = null;
            executor = null;
            if (hub.needTrace()) hub.trace(task, "Caught Exception:" + produceException.toString());
        }

        void finish() {
            status = TaskStatus.Finished;
            isProduced = true;
            finishTime = System.currentTimeMillis();
            hub.taskFinishReport(task);
            caller = null;
            executor = null;
            if (hub.needTrace()) hub.trace(task, "Finish executing,result:" + produceResult);
        }

        void overtime() {
            status = TaskStatus.Overtime;
            task.isProduced = true;
            caller = null;
            executor = null;
            if (hub.needTrace()) hub.trace(task, "Task overtime:" + task.abortDuration);
        }
    }
}
