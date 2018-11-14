package sf.task;

import java.util.LinkedList;
import java.util.Queue;

class SimpleTaskQueue
{
	private Queue<Task> queue_0 = new LinkedList<>();
	private Queue<Task> queue_1 = new LinkedList<>();

	private final Object lock_queue_1 = "(*^ω^*)";

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
					Queue<Task> queue = queue_0;
					queue_0.clear();
					queue_0 = queue_1;
					queue_1 = queue;
					return queue_0.size() > 0;
				}
			}
		}
	}
}
