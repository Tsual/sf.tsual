package sf.uds.common;

public interface IAsyncIterable<T> {
    T next();
    void add(T obj);
}
