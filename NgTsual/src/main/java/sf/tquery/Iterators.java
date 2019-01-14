package sf.tquery;

import sf.uds.common.Iterable;
import sf.util.ObjectHelper;

import java.util.Objects;

public final class Iterators {
    public static <T> Iterator<T> get(java.lang.Iterable<T> it) {
        return new BasicIterator<>(Objects.requireNonNull(it));
    }

    public static <T> Iterator<T> get(T[] ar) {
        return new BasicIterator<>(Objects.requireNonNull(ar));
    }

    public static <T> Iterator<T> get(Iterable<T> it) {
        return new BasicIterator<>(Objects.requireNonNull(it));
    }
}