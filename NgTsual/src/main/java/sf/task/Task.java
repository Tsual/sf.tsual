package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

public class Task<T>
{
	TaskHub hub;
	boolean isProduced = false, isAborted = false, need_schedule_abort = false;
	Long startTime, executeTime, finishTime;

	IExec_0<T> executable;
	T produceResult = null;
	Exception produceException = null;
	Thread caller, executor;

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
		synchronized (hub.any_finish_lock) {
			hub.any_finish_lock.notifyAll();
		}
	}

	Task(TaskHub hub, IExec_0<T> executable)
	{
		this.hub = hub;
		this.executable = executable;
		startTime = System.currentTimeMillis();
		caller = Thread.currentThread();
	}

	public boolean isProduced()
	{
		return isProduced;
	}

	public T getProduceResult() throws Exception
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
}
