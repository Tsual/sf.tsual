package sf.uds.common;

public interface OnetimeIterable<T> {
    boolean hasNext() throws Exception;

    T next() throws Exception;
}
