package sf.task;


abstract class AbsTaskQueue<T extends Task> {
    abstract T element();

    abstract void remove(T task);

    abstract void add(T task);

    abstract boolean remain();

    TaskHost host;
    Long remind_host_time;

    protected final void remind_host() {
        if (host != null)
            synchronized (host.add_daemon_lock) {
                host.add_daemon_lock.notify();
            }
    }
}
