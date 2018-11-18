package sf.task;


import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

public class TaskHost implements AutoCloseable
{
	private final String name;
	private final ThreadGroup thread_group;
	private final Integer max_worker_count;

	//region locks
	volatile boolean is_add_daemon_alive = true;
	final Object add_daemon_lock = "ヾ(^▽^*)))";

	private final Object host_lock = "(இωஇ )";


	private final Object workers_lock = "（▼へ▼メ）";
	private final boolean[] thread_close = {false};
	//endregion

	private final SimpleTaskQueue simpleTaskQueue = new SimpleTaskQueue();
	private final List<Thread> fail_workers = new ArrayList<>();
	//private Thread[] workers;

	private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (thread, ex) ->
	{
		ex.printStackTrace();
		start_worker();
	};

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
					syncThreadLocal(exec.caller);
					exec.executeTime = System.currentTimeMillis();
					try {
						if (exec.need_schedule_abort) {
							exec.executor = Thread.currentThread();
							exec.produceResult = exec.executable.execute();
						} else {
							exec.produceResult = exec.executable.execute();
						}
					} catch (Exception ex) {
						exec.produceException = ex;
					} finally {
						exec.finishTask();
					}

				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	};


	private void resetWorker(Thread worker)
	{
		AccessController.doPrivileged((PrivilegedAction<Object>) () ->
		{
			try {
				final Field threadStatus = Thread.class.getDeclaredField("threadStatus");
				threadStatus.setAccessible(true);
				threadStatus.set(worker, 0);

				final Field group = Thread.class.getDeclaredField("group");
				group.setAccessible(true);
				group.set(worker, thread_group);

				final Field eetop = Thread.class.getDeclaredField("eetop");
				eetop.setAccessible(true);
				eetop.set(worker, 0L);

				final Field target = Thread.class.getDeclaredField("target");
				target.setAccessible(true);
				target.set(worker, thread_exec_shell);

				worker.setUncaughtExceptionHandler(uncaughtExceptionHandler);
				worker.run();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}

	private void syncThreadLocal(Thread caller)
	{
		AccessController.doPrivileged((PrivilegedAction<Object>) () ->
		{
			try {
				Field threadLocals = Thread.class.getDeclaredField("threadLocals");
				threadLocals.setAccessible(true);
				threadLocals.set(Thread.currentThread(), threadLocals.get(caller));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			return null;
		});
	}

	void abort_task(Task task, TaskHub hub)
	{
		System.out.println("aborting..." + task);
		if (task.isProduced) return;
		task.isProduced = true;
		task.finishTime = System.currentTimeMillis();
		if (!hub.anyFinish)
			hub.anyFinish = true;
		if (task.executor != null) {
			System.out.println("reseting..." + task.executor);
			task.executor.interrupt();
			resetWorker(task.executor);
		} else {
			System.out.println("removing..." + task);
			simpleTaskQueue.remove(task);
		}

		task.caller = null;
		task.executor = null;
		task.notifyFinish();
	}

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

		start_worker_add_daemon();
		//start_worker_reset_daemon();
	}

	private void start_worker_add_daemon()
	{
		new Thread(() ->
		{
			while (true) {
				if (thread_close[0]) return;
				try {
					synchronized (add_daemon_lock) {
						add_daemon_lock.wait();
						if (thread_group.activeCount() < this.max_worker_count)
							start_worker();
						else {
							is_add_daemon_alive = false;
							return;
						}
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
			worker.setUncaughtExceptionHandler(uncaughtExceptionHandler);
			worker.start();
			//workers = new Thread[thread_group.activeCount()];
			//thread_group.enumerate(workers, false);
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

		void remove(Task task)
		{
			synchronized (queue_1) {
				if (queue_1.contains(task))
					queue_1.remove(task);
				else {
					synchronized (this) {
						if (queue_0.contains(task))
							queue_0.remove(task);
					}
				}
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
							synchronized (host.add_daemon_lock) {
								host.add_daemon_lock.notify();
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
