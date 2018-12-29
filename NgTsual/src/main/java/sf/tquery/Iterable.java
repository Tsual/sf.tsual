package sf.tquery;

import java.util.List;

public interface Iterable<T> {
    boolean hasNext() throws Exception;

    T next() throws Exception;

    void reset() throws Exception;

    interface SettledIterable<T> {
        List<T> settleList() throws Exception;
    }
}

