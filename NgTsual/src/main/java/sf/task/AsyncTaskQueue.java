package sf.task;

import java.util.concurrent.ConcurrentLinkedQueue;

class AsyncTaskQueue extends ConcurrentLinkedQueue<Task> implements ITaskQueue<Task> {
    private TaskHost host;
    private long delay;

    public AsyncTaskQueue(TaskHost host, long delay) {
        this.host = host;
        this.delay = delay;
    }

    @Override
    public TaskHost getHost() {
        return host;
    }

    @Override
    public boolean add(Task task) {
        return offer(task);
    }

    @Override
    public Task next() {
        final Task next = poll();
        if (next != null && System.currentTimeMillis() - next.startTime > delay)
            remind_host();
        return next;
    }
}
