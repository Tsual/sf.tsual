package sf.task;


import java.util.ArrayList;
import java.util.List;

public class TaskHost implements AutoCloseable
{
	private static final ThreadGroup THREAD_GROUP = new ThreadGroup("TaskHost");
	private final Object lock = new Object();
	private final boolean[] thread_close = {false};

	private List<Thread> workers = new ArrayList<>();
	private SimpleTaskQueue simpleTaskQueue = new SimpleTaskQueue();

	private final Runnable thread_exec_shell = () ->
	{
		while (true) {
			if (thread_close[0])
				break;
			try {
				Task exec = null;
				synchronized (lock) {
					if (simpleTaskQueue.remain()) {
						exec = simpleTaskQueue.get();
						lock.notifyAll();
					} else {
						lock.wait();
						if (thread_close[0])
							break;
					}
				}

				if (exec != null) {
					try {
						exec.result = exec.executable.execute();
					} catch (Exception ex) {
						exec.ex = ex;
					}
					exec.finishTask();
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	};

	public TaskHost(final int worker_count)
	{
		for (int i = 0; i < worker_count; i++) {
			final Thread thread = new Thread(THREAD_GROUP, thread_exec_shell, "TaskWorker-" + i);
			workers.add(thread);
			thread.start();
		}
	}

	void addTask(Task task)
	{
		simpleTaskQueue.add(task);
		synchronized (lock) {
			lock.notifyAll();
		}
	}

	public TaskHub newTaskHub() throws Exception
	{
		return new TaskHub(this);
	}

	@Override
	public void close() throws Exception
	{
		thread_close[0] = true;
		synchronized (lock) {
			lock.notifyAll();
		}
	}
}
