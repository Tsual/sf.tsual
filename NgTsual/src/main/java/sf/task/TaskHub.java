package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

import java.util.ArrayList;
import java.util.List;

public class TaskHub
{
	private final static int size = 10;
	private final static int size_m1 = size - 1;

	private TaskHost host;
	private List<Task> tasks = new ArrayList<>();

	final Object any_complete_notify_object = "(*˘︶˘*).。.:*♡";
	private boolean anyComplete = false;

	private final Long allow_delay;
	final Long[] delays = new Long[size_m1];
	int index = 0;


	TaskHub(TaskHost host, Long allow_delay)
	{
		this.host = host;
		this.allow_delay = allow_delay * size;
	}

	void finishTask(Task task)
	{
		if (!anyComplete)
			anyComplete = true;
		long all = task.finishTime - task.startTime;
		synchronized (delays) {
			if (index < size_m1)
				delays[index++] = all;
			else {
				index = 0;
				for (int i = 0; i < size_m1; i++) {
					all += delays[i];
					delays[i] = 0L;
				}
			}
		}
		if (all > allow_delay)
			synchronized (host.daemon_lock) {
				host.daemon_lock.notifyAll();
			}
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
		int size = tasks.size();
		for (int i = 0; i < size; i++) {
			synchronized (tasks.get(i)) {
				if (!tasks.get(i).isProduced()) {
					try {
						tasks.get(i).wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
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
