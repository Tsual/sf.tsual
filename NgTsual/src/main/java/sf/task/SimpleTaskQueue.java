package sf.task;

import java.util.LinkedList;
import java.util.Queue;

class SimpleTaskQueue extends AbsTaskQueue {
    private Queue<Task> queue_0 = new LinkedList<>();
    private Queue<Task> queue_1 = new LinkedList<>();
    private final String lock_queue_1 = "(*^ω^*)";

    public Task element() {
        synchronized (this) {
            return queue_0.poll();
        }
    }

    public void remove(Task task) {
        synchronized (queue_1) {
            if (queue_1.contains(task))
                queue_1.remove(task);
            else {
                synchronized (this) {
                    queue_0.remove(task);
                }
            }
        }
    }

    public void add(Task task) {
        synchronized (lock_queue_1) {
            queue_1.offer(task);
        }
    }

    public boolean remain() {
        synchronized (this) {
            if (queue_0.size() > 0)
                return true;
            else {
                synchronized (lock_queue_1) {
                    if (queue_1.size() == 0) return false;
                    if (System.currentTimeMillis() - queue_1.element().startTime > remind_host_time)
                        remind_host();
                    Queue<Task> queue = queue_0;
                    queue_0 = queue_1;
                    queue_1 = queue;
                    return true;
                }
            }
        }
    }

}
