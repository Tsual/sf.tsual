package sf.uds.common;


public interface Iterable<T> {
    void reset() throws Exception;

    boolean hasNext() throws Exception;

    T next() throws Exception;
}

