package sf.task;


import java.util.LinkedList;
import java.util.Queue;

public class TaskHost implements AutoCloseable
{
	private final String name;
	private final ThreadGroup thread_group;
	private final Integer max_worker_count;

	//region locks
	final Object daemon_lock = "ヾ(^▽^*)))";
	private final Object host_lock = "(இωஇ )";
	private final Object workers_lock = "（▼へ▼メ）";
	private final boolean[] thread_close = {false};
	//endregion

	private final SimpleTaskQueue simpleTaskQueue = new SimpleTaskQueue();
	private Thread[] workers;

	private final Runnable thread_exec_shell = () ->
	{
		while (true) {
			if (thread_close[0])
				break;
			try {
				Task exec = null;
				synchronized (host_lock) {
					if (simpleTaskQueue.remain()) {
						exec = simpleTaskQueue.get();
						host_lock.notify();
					} else {
						host_lock.wait();
						if (thread_close[0])
							break;
					}
				}

				if (exec != null) {
					exec.executeTime = System.currentTimeMillis();
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

	public TaskHost(String name, Integer start_worker_count, Integer max_worker_count, Long allow_wait_time)
	{
		if (start_worker_count < 4 || max_worker_count < 5 || max_worker_count <= start_worker_count) {
			start_worker_count = 5;
			max_worker_count = 25;
		}
		this.simpleTaskQueue.ptx_time = allow_wait_time > 50 ? allow_wait_time : 200;
		this.simpleTaskQueue.host = this;
		this.name = name;
		this.thread_group = new ThreadGroup("TaskHost-" + name);
		this.max_worker_count = max_worker_count;
		while (start_worker_count-- > 0)
			start_worker();
		new Thread(() ->
		{
			while (true) {
				if (thread_close[0]) return;
				try {
					synchronized (daemon_lock) {
						daemon_lock.wait();
						if (thread_group.activeCount() < this.max_worker_count)
							start_worker();
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();
	}

	private void start_worker()
	{
		synchronized (workers_lock) {
			final Thread worker = new Thread(thread_group, thread_exec_shell, "TaskWorker-" + thread_group.activeCount());
			worker.setUncaughtExceptionHandler((thread, ex) ->
			{
				ex.printStackTrace();
				start_worker();
				throw new RuntimeException(ex);
			});
			worker.start();
			workers = new Thread[thread_group.activeCount()];
			thread_group.enumerate(workers, false);
		}
	}

	void addTask(Task task)
	{
		simpleTaskQueue.add(task);
		synchronized (host_lock) {
			host_lock.notify();
		}
	}

	public TaskHub newTaskHub(Long allow_delay)
	{
		return new TaskHub(this, allow_delay);
	}

	ThreadGroup getThreadGroup()
	{
		return thread_group;
	}

	@Override
	public void close() throws Exception
	{
		thread_close[0] = true;
		synchronized (host_lock) {
			host_lock.notifyAll();
		}
	}

	public String getName()
	{
		return name;
	}

	private class SimpleTaskQueue
	{
		private Queue<Task> queue_0 = new LinkedList<>();
		private Queue<Task> queue_1 = new LinkedList<>();

		private final Object lock_queue_1 = "(*^ω^*)";

		Long ptx_time;
		TaskHost host;

		Task get()
		{
			synchronized (this) {
				return queue_0.poll();
			}
		}

		void add(Task task)
		{
			synchronized (lock_queue_1) {
				queue_1.offer(task);
			}
		}

		boolean remain()
		{
			synchronized (this) {
				if (queue_0.size() > 0)
					return true;
				else {
					synchronized (lock_queue_1) {
						if (queue_1.size() == 0) return false;
						if (System.currentTimeMillis() - queue_1.element().startTime > ptx_time)
							synchronized (host.daemon_lock) {
								host.daemon_lock.notify();
							}
						Queue<Task> queue = queue_0;
						queue_0 = queue_1;
						queue_1 = queue;
						return true;
					}
				}
			}
		}
	}
}
