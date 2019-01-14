package sf.uds.common;


public interface Iterable<T> extends OnetimeIterable<T> {
    void reset() throws Exception;
}

