package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

public class TaskHub
{
	private TaskHost host;

	TaskHub(TaskHost host)
	{
		this.host = host;
	}

	public <T> Task<T> execute(IExec_0<T> executable, long abort_time)
	{
		//todo:execute task
		return null;
	}

	public void WaitAll()
	{
		return;
	}
}
