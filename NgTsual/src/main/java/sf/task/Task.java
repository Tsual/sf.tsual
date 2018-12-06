package sf.task;

import sf.uds.interfaces.del.IExec_0;

public class Task<T>
{
	TaskHub hub;
	volatile boolean isProduced = false;
	Long startTime, executeTime, finishTime, abortDuration;

	IExec_0<T> executable;
	T produceResult = null;
	Exception produceException = null;
	Thread caller, executor;
	TaskStatus status = TaskStatus.Created;
	ThreadLocalOperation tlOperation = ThreadLocalOperation.None;
	private final Object sf_lock = "($_$)";

	void finishTask()
	{
		if (!isProduced) {
			isProduced = true;
			finishTime = System.currentTimeMillis();
			hub.taskFinishReport(this);
			caller = null;
			executor = null;
		}
	}

	void notifyFinish()
	{
		hub.finish_count++;
		synchronized (hub.wait_lock) {
			hub.wait_lock.notifyAll();
		}
		synchronized (sf_lock) {
			sf_lock.notifyAll();
		}
	}

	Task(TaskHub hub, IExec_0<T> executable)
	{
		this.hub = hub;
		this.executable = executable;
		this.startTime = System.currentTimeMillis();
		this.caller = Thread.currentThread();
	}

	public T awaitResult() throws Exception
	{
		await();
		if (produceException != null) throw produceException;
		return produceResult;
	}

	public T awaitResultClose() throws Exception
	{
		await();
		if(hub!=null)hub.getTasks().remove(this);
		if (produceException != null) throw produceException;
		return produceResult;
	}

	public void await()
	{
		synchronized (sf_lock) {
			try {
				if (!isProduced)
					sf_lock.wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public Long getStartTime()
	{
		return startTime;
	}

	public Long getFinishTime()
	{
		return finishTime;
	}

	public TaskStatus getStatus()
	{
		return status;
	}

	public boolean isProduced()
	{
		return isProduced;
	}

	@Override
	public String toString()
	{
		return "AsyncTask-" + hashCode();
	}

	String getTraceInfo()
	{
		return "Task{" +
				"isProduced=" + isProduced +
				", startTime=" + startTime +
				", executeTime=" + executeTime +
				", finishTime=" + finishTime +
				", abortDuration=" + abortDuration +
				", produceResult=" + produceResult +
				", produceException=" + produceException +
				'}';
	}
}
