package sf.task;

public class Task<T>
{
	private TaskHub hub;

	Task(TaskHub hub)
	{
		this.hub = hub;
	}


	public T WaitResult()
	{
		return null;
	}
}
