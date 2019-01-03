package sf.tquery;

import sf.util.ObjectHelper;

public final class Iterators {
    public static <T> Iterator<T> get(java.lang.Iterable<T> it) {
        return new BasicIterator<>(ObjectHelper.requireNotNull(it));
    }

    public static <T> Iterator<T> get(T[] ar) {
        return new BasicIterator<>(ObjectHelper.requireNotNull(ar));
    }

    public static <T> Iterator<T> get(Iterable<T> it) {
        return new BasicIterator<>(ObjectHelper.requireNotNull(it));
    }
}