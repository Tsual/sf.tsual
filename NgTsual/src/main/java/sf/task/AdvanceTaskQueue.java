package sf.task;

import sf.util.AsyncQueue;

import java.util.ArrayList;
import java.util.List;

class AdvanceTaskQueue extends AsyncQueue<Task> implements ITaskQueue<Task> {
    private TaskHost taskHost;
    private long delay;
    private List<Task> rl = new ArrayList<>();

    AdvanceTaskQueue(TaskHost taskHost, long delay) {
        super(Task.class, 32, 4);
        this.taskHost = taskHost;
        this.delay = delay;
    }

    @Override
    public TaskHost getHost() {
        return taskHost;
    }

    @Override
    public void remove(Task task) {
        rl.add(task);
    }

    @Override
    public Task next() {
        final Task next = super.next();
        if (rl.contains(next)) {
            rl.remove(next);
            return next();
        }
        if (next != null && System.currentTimeMillis() - next.startTime > delay)
            remind_host();
        return next;
    }
}