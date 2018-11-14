package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

import java.util.ArrayList;
import java.util.List;

public class TaskHub
{
	private TaskHost host;
	private List<Task> tasks = new ArrayList<>();

	final Object any_complete_notify_object = "(*˘︶˘*).。.:*♡";
	private boolean anyComplete = false;


	TaskHub(TaskHost host)
	{
		this.host = host;
	}

	void finishTask(Task task)
	{
		anyComplete = true;
	}

	public <T> Task<T> execute(IExec_0<T> executable)
	{
		Task<T> task = new Task<>(this, executable);
		tasks.add(task);
		host.addTask(task);
		return task;
	}

	public void waitAll()
	{
		for (Task task : tasks) {
			synchronized (task) {
				if (!task.isProduced()) {
					try {
						task.wait();
					} catch (InterruptedException ignored) {
					}
				}
			}
		}
	}

	public void waitAny()
	{
		if (!anyComplete) {
			synchronized (any_complete_notify_object) {
				try {
					any_complete_notify_object.wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public List<Task> getTasks()
	{
		return tasks;
	}

}
