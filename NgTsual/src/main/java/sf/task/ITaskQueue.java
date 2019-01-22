package sf.task;

import sf.uds.common.IConcurrentIterable;

public interface ITaskQueue<T extends Task> extends IConcurrentIterable<T> {
    TaskHost getHost();

    default void remind_host() {
        final TaskHost host = getHost();
        if (host != null)
            synchronized (host.add_daemon_lock) {
                host.add_daemon_lock.notify();
            }
    }
}
