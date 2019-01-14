package sf.task;


import sf.uds.common.OnetimeIterable;

abstract class AbsTaskQueue<T extends Task> implements OnetimeIterable<T> {
    abstract public T next();

    abstract void remove(T task);

    abstract void add(T task);

    abstract public boolean hasNext();

    TaskHost host;
    Long remind_host_time;

    protected final void remind_host() {
        if (host != null)
            synchronized (host.add_daemon_lock) {
                host.add_daemon_lock.notify();
            }
    }
}
