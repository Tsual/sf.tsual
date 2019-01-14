package sf.task;

import sf.util.AdvanceAsyncQueue;

class AdvanceTaskQueue extends AdvanceAsyncQueue<Task> implements ITaskQueue<Task> {
    private TaskHost taskHost;
    private long delay;

    AdvanceTaskQueue(TaskHost taskHost, long delay) {
        super(Task.class, 64, 64);
        this.taskHost = taskHost;
        this.delay = delay;
    }

    @Override
    public TaskHost getHost() {
        return taskHost;
    }

    @Override
    public Task next() {
        final Task next = super.next();
        if (System.currentTimeMillis() - next.startTime > delay)
            remind_host();
        return next;
    }
}