package sf.uds.common;

public interface IConcurrentIterable<T> {
    T next();
    boolean add(T obj);
}
