package sf.task;

import sf.uds.del.IExec_0;

public class Task<T> {
    TaskHub hub;
    volatile boolean isProduced = false;
    Long startTime, executeTime, finishTime, abortDuration;

    IExec_0<T> executable;
    T produceResult = null;
    Exception produceException = null;
    Thread caller, executor;
    TaskStatus status = TaskStatus.Created;
    ThreadLocalOperation tlOperation = ThreadLocalOperation.None;
    private final Object sf_lock = "($_$)";
    final Object exec_lock = new Object();

    void finishTask() {
        if (!isProduced) {
            isProduced = true;
            finishTime = System.currentTimeMillis();
            hub.taskFinishReport(this);
            caller = null;
            executor = null;
            if (this.hub.needTrace())
                if (produceException == null)
                    this.hub.trace(this, "Finish executing,result:" + this.produceResult);
                else
                    this.hub.trace(this, "Caught Exception:" + produceException.toString());
        }
    }

    void notifyFinish() {
        synchronized (hub.finish_lock) {
            hub.finish_count++;
        }
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
        synchronized (sf_lock) {
            try {
                if (!isProduced)
                    sf_lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void await(long second) {
        synchronized (sf_lock) {
            try {
                if (!isProduced)
                    sf_lock.wait(second);
                if (!isProduced)
                    sync_bb();
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

    Task<T> sync_bb() {
        if (!isProduced)
            synchronized (exec_lock) {
                if (isProduced) return this;
                hub.host.task_queue.remove(this);
                this.executeTime = System.currentTimeMillis();
                this.status = TaskStatus.Executing;
                this.executor = Thread.currentThread();

                if (this.hub.needTrace())
                    this.hub.trace(this, this.executor.getName() + "<<Begin executing");
                try {
                    this.produceResult = this.executable.execute();
                    this.status = TaskStatus.Finished;

                } catch (InterruptedException ignored) {
                } catch (Exception ex) {
                    this.produceException = ex;
                    this.status = TaskStatus.Error;
                } finally {
                    this.finishTask();
                    this.notifyFinish();
                }
            }
        return this;
    }

    public T syncExecute() throws Exception {
        return sync_bb().getResult();
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
}
