package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

public class Task<T>
{
	private TaskHub hub;
	boolean isProduced = false, isAborted = false, need_schedule_abort = false;
	Long startTime, executeTime, finishTime;

	IExec_0<T> executable;
	T produceResult = null;
	Exception produceException = null;
	Thread caller, executor;
	TaskStatus status = TaskStatus.Created;

	void finishTask()
	{
		if (!isProduced) {
			isProduced = true;
			finishTime = System.currentTimeMillis();
			hub.taskFinishReport(this);
			caller = null;
			executor = null;
			notifyFinish();
		}
	}

	void notifyFinish()
	{
		System.out.println("notifyFinish");
		synchronized (hub.any_finish_lock) {
			hub.any_finish_lock.notify();
		}
	}

	Task(TaskHub hub, IExec_0<T> executable)
	{
		this.hub = hub;
		this.executable = executable;
		startTime = System.currentTimeMillis();
		caller = Thread.currentThread();
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
