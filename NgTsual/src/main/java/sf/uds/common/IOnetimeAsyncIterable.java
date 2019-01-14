package sf.uds.common;

public interface IOnetimeAsyncIterable<T> {
    T next();
    void add(T obj);
}
