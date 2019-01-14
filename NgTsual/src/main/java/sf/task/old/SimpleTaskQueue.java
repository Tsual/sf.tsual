package sf.task.old;

import sf.task.ITaskQueue;
import sf.task.Task;
import sf.task.TaskHost;

import java.util.LinkedList;
import java.util.Queue;

class SimpleTaskQueue implements ITaskQueue<Task> {
    private Queue<Task> queue_0 = new LinkedList<>();
    private Queue<Task> queue_1 = new LinkedList<>();
    private final String lock_queue_1 = "(*^ω^*)";
    private TaskHost host;
    private Long remind_host_time;

    public SimpleTaskQueue(TaskHost host, Long remind_host_time) {
        this.host = host;
        this.remind_host_time = remind_host_time;
    }

    public Task next() {
        synchronized (this) {
            return hasNext() ? queue_0.poll() : null;
        }
    }

    @Override
    public TaskHost getHost() {
        return host;
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

    private boolean hasNext() {
        if (queue_0.size() > 0)
            return true;
        else {
            synchronized (lock_queue_1) {
                if (queue_1.size() == 0) return false;
                if (System.currentTimeMillis() - queue_1.element().getStartTime() > remind_host_time)
                    remind_host();
                Queue<Task> queue = queue_0;
                queue_0 = queue_1;
                queue_1 = queue;
                return true;
            }
        }
    }

}
