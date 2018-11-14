package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

public class Task<T>
{
	private TaskHub hub;

	IExec_0<T> executable;

	private boolean isProduced = false;
	T result = null;
	Exception ex = null;

	void finishTask()
	{
		isProduced = true;
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
}
