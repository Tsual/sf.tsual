package sf.task;

import sf.uds.interfaces.del.executable.IExec_0;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskHub implements AutoCloseable
{
	private final static int report_cache_size = 10;
	private final static int report_cache_size_m1 = report_cache_size - 1;

	private TaskHost host;
	private List<Task> tasks = new ArrayList<>();
	private OutputStream traceOutputStream;

	final Object any_finish_lock = "(*˘︶˘*).。.:*♡";
	private final Object wait_all_lock = "(*˘︶˘*).。.:*♡1";
	private final Object wait_any_lock = "(*˘︶˘*).。.:*♡2";
	boolean anyFinish = false;

	int finish_count = 0;
	private int index = 0;
	private final Long allow_delay;
	private final Long[] delays = new Long[report_cache_size_m1];
	private Timer abort_schedule;

	private boolean[] close = {false};


	TaskHub(TaskHost host, Long allow_delay, OutputStream traceOutputStream)
	{
		this.host = host;
		this.allow_delay = allow_delay * report_cache_size;
		this.traceOutputStream = traceOutputStream;
		new Thread(() ->
		{
			while (true) {
				synchronized (any_finish_lock) {
					try {
						any_finish_lock.wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					if (close[0]) return;
				}
				synchronized (wait_all_lock) {
					wait_all_lock.notifyAll();
				}
				synchronized (wait_any_lock) {
					wait_any_lock.notifyAll();
				}

			}
		}).start();
	}

	boolean needTrace()
	{
		return traceOutputStream != null;
	}

	void trace(Task task, String msg)
	{
		try {
			if (traceOutputStream != null)
				traceOutputStream.write((task.toString() + "<<" + new Timestamp(System.currentTimeMillis()) + "<<" + msg + "\n").getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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

	public <T> Task<T> execute(IExec_0<T> executable, ThreadLocalOperation threadLocalOperation)
	{
		Task<T> task = new Task<>(this, executable);
		if (threadLocalOperation != null)
			task.tlOperation = threadLocalOperation;
		tasks.add(task);
		host.addTask(task);
		return task;
	}

	public <T> Task<T> execute(IExec_0<T> executable, ThreadLocalOperation threadLocalOperation, Long abortDuration)
	{
		Task<T> task = new Task<>(this, executable);
		if (threadLocalOperation != null)
			task.tlOperation = threadLocalOperation;
		if (10 < abortDuration) {
			if (abort_schedule == null)
				abort_schedule = new Timer();
			task.abortDuration = abortDuration;
			TaskHub hub = this;
			abort_schedule.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					try {
						host.abort_task(task, hub);
					} catch (Throwable ex) {
						ex.printStackTrace();
					}
				}
			}, abortDuration);
		}
		tasks.add(task);
		host.addTask(task);
		return task;
	}

	public void waitAll()
	{
		int size = tasks.size();
		while (true) {
			synchronized (wait_all_lock) {
				try {
					wait_all_lock.wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				if (finish_count >= size) return;
			}
		}
	}

	public void waitAny()
	{
		if (!anyFinish) {
			synchronized (wait_any_lock) {
				try {
					wait_any_lock.wait();
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

	@Override
	public void close() throws Exception
	{
		close[0] = true;
		synchronized (any_finish_lock) {
			any_finish_lock.notifyAll();
		}
	}
}
