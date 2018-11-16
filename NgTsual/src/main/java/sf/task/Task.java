package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

public class Task<T>
{
	private TaskHub hub;
	private boolean isProduced = false;
	Long startTime;
	Long executeTime;
	Long finishTime;

	IExec_0<T> executable;
	T result = null;
	Exception ex = null;
	Thread call_thread;


	void finishTask()
	{
		isProduced = true;
		finishTime = System.currentTimeMillis();

		hub.finishTask(this);
		synchronized (this) {
			notifyAll();
		}
		synchronized (hub.any_complete_notify_object) {
			hub.any_complete_notify_object.notifyAll();
		}
	}

	Task(TaskHub hub, IExec_0<T> executable)
	{
		this.hub = hub;
		this.executable = executable;
		startTime = System.currentTimeMillis();
		call_thread = Thread.currentThread();
	}

	public boolean isProduced()
	{
		return isProduced;
	}

	public T getResult() throws Exception
	{
		if (ex != null) throw ex;
		return result;
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
