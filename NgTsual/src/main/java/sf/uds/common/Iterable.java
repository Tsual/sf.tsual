package sf.uds.common;


public interface Iterable<T> extends OnceIterable<T> {
    void reset() throws Exception;
}

