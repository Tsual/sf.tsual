package sf.task;

import java.util.ArrayList;
import java.util.List;

public class TaskHost
{
	private final List<Thread> workers = new ArrayList<Thread>();
	private final List<Thread> hosts = new ArrayList<Thread>();

	public void init(int worker_count, int host_count)
	{
		while (worker_count-- > 0) {

		}

		while (host_count-- > 0) {

		}
		//todo:init class
	}

	public TaskHub newTaskHub() throws Exception
	{
		return new TaskHub(this);
	}
}
