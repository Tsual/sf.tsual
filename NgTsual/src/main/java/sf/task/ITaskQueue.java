package sf.task;

import sf.uds.common.IAsyncIterable;

public interface ITaskQueue<T extends Task> extends IAsyncIterable<T> {
    TaskHost getHost();

    void remove(T task);

    void add(T task);

    default void remind_host() {
        final TaskHost host = getHost();
        if (host != null)
            synchronized (host.add_daemon_lock) {
                host.add_daemon_lock.notify();
            }
    }
}
