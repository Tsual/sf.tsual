package sf.task;

public class HubTaskQueue<T extends Task> extends AbsTaskQueue<T> {
    @Override
    public T get() {
        return null;
    }

    @Override
    public void remove(T task) {

    }

    @Override
    public void add(T task) {

    }

    @Override
    public boolean remain() {
        return false;
    }
}
