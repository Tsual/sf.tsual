package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

import java.sql.Timestamp;

public class Task<T>
{
	TaskHub hub;
	volatile boolean isProduced = false;
	Long startTime, executeTime, finishTime, abortDuration;

	IExec_0<T> executable;
	T produceResult = null;
	Exception produceException = null;
	Thread caller, executor;
	TaskStatus status = TaskStatus.Created;
	ThreadLocalOperation tlOperation = ThreadLocalOperation.None;

	void finishTask()
	{
		if (!isProduced) {
			isProduced = true;
			finishTime = System.currentTimeMillis();
			hub.taskFinishReport(this);
			caller = null;
			executor = null;
		}
	}

	void notifyFinish()
	{
		synchronized (hub.any_finish_lock) {
			hub.finish_count++;
			hub.any_finish_lock.notify();
		}
	}

	Task(TaskHub hub, IExec_0<T> executable)
	{
		this.hub = hub;
		this.executable = executable;
		this.startTime = System.currentTimeMillis();
		this.caller = Thread.currentThread();
	}

	public T getResult() throws Exception
	{
		if (produceException != null) throw produceException;
		return produceResult;
	}

	public void await()
	{
		try {
			synchronized (this) {
				wait();
			}
		} catch (InterruptedException ignored) {
		}
	}

	public Long getStartTime()
	{
		return startTime;
	}

	public Long getFinishTime()
	{
		return finishTime;
	}

	public TaskStatus getStatus()
	{
		return status;
	}

	public boolean isProduced()
	{
		return isProduced;
	}
}
