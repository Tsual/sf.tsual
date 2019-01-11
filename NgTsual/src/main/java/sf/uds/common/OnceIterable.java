package sf.uds.common;

public interface OnceIterable<T> {
    boolean hasNext() throws Exception;

    T next() throws Exception;
}
