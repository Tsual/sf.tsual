package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskHub
{
	private final static int report_cache_size = 10;
	private final static int report_cache_size_m1 = report_cache_size - 1;

	private TaskHost host;
	private List<Task> tasks = new ArrayList<>();

	final Object any_finish_lock = "(*˘︶˘*).。.:*♡";
	boolean anyFinish = false;

	private int finish_count = 0, index = 0;
	private final Long allow_delay;
	private final Long[] delays = new Long[report_cache_size_m1];
	private Timer abort_schedule;


	TaskHub(TaskHost host, Long allow_delay)
	{
		this.host = host;
		this.allow_delay = allow_delay * report_cache_size;
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (true) {
					synchronized (any_finish_lock) {
						try {
							any_finish_lock.wait();
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
						finish_count++;
					}
				}
			}
		}).start();
	}

	void taskFinishReport(Task task)
	{
		if (!anyFinish)
			anyFinish = true;
		long all = task.finishTime - task.startTime;
		synchronized (delays) {
			if (index < report_cache_size_m1)
				delays[index++] = all;
			else {
				index = 0;
				for (int i = 0; i < report_cache_size_m1; i++) {
					all += delays[i];
					delays[i] = 0L;
				}
			}
		}
		if (all > allow_delay && host.is_add_daemon_alive)
			synchronized (host.add_daemon_lock) {
				host.add_daemon_lock.notifyAll();
			}
	}

	public <T> Task<T> execute(IExec_0<T> executable)
	{
		Task<T> task = new Task<>(this, executable);
		tasks.add(task);
		host.addTask(task);
		return task;
	}

	public <T> Task<T> execute(IExec_0<T> executable, Long abort_time)
	{
		TaskHub hub = this;
		Task<T> task = new Task<>(this, executable);
		task.need_schedule_abort = true;
		if (abort_schedule == null)
			abort_schedule = new Timer();
		abort_schedule.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				host.abort_task(task, hub);
			}
		}, abort_time);
		tasks.add(task);
		host.addTask(task);
		return task;
	}

	public void waitAll()
	{
		int size = tasks.size() + finish_count;
		while (true) {
			synchronized (any_finish_lock) {
				if (finish_count >= size)
					return;
				any_finish_lock.notify();
			}
		}
	}

	public void waitAny()
	{
		if (!anyFinish) {
			synchronized (any_finish_lock) {
				try {
					any_finish_lock.wait();
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
