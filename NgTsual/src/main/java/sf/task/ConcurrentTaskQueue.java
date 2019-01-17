package sf.task;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentTaskQueue implements ITaskQueue<Task> {
    private ConcurrentLinkedQueue<Task> queue = new ConcurrentLinkedQueue<>();
    private TaskHost host;
    private long delay;

    public ConcurrentTaskQueue(TaskHost host, long delay) {
        this.host = host;
        this.delay = delay;
    }

    @Override
    public TaskHost getHost() {
        return host;
    }

    @Override
    public void remove(Task task) {
        queue.remove(task);
    }

    @Override
    public void add(Task task) {
        queue.offer(task);
    }

    @Override
    public Task next() {
        final Task next = queue.poll();
        if (next != null && System.currentTimeMillis() - next.startTime > delay)
            remind_host();
        return next;
    }
}
