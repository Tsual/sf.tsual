package sf.tquery;

import sf.tquery.irunshell.IAction;
import sf.tquery.irunshell.IRunnable;
import sf.tquery.irunshell.ISelector;
import sf.tquery.irunshell.ITypeConverter;
import sf.uds.common.Iterable;
import sf.uds.common.IListable;
import sf.uds.common.SettledIterable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public interface Iterator<T> extends Iterable<T>, IListable<T>, SettledIterable<T>, java.lang.Iterable<T> {
    default Iterator<T> foreach(IRunnable<T> runnable) throws Exception {
        Objects.requireNonNull(runnable);
        reset();
        while (hasNext())
            runnable.run(next());
        return this;
    }

    //trans one element to one of V
    <V> Iterator<V> as(ITypeConverter<T, V> tvTypeConverter);

    //trans one element to collection of V
    <V> Iterator<V> map(ITypeConverter<T, Iterable<V>> tvTypeConverter);

    Iterator<T> sort(Comparator<? super T> comparator);

    Iterator<T> where(ISelector<T> tSelector);

    default <V> V execute(IAction<V> action) throws Exception {
        return Objects.requireNonNull(action).execute();
    }

    Iterator<T> add(T item) throws Exception;

    Iterator<T> add(T[] array) throws Exception;

    Iterator<T> add(java.lang.Iterable<T> Iterable) throws Exception;

    Iterator<T> add(Iterable<T> Iterator) throws Exception;

    //region first
    default T first() throws Exception {
        reset();
        return hasNext() ? next() : null;
    }

    default T first(ISelector<T> tSelector) throws Exception {
        Objects.requireNonNull(tSelector);
        reset();
        while (hasNext()) {
            T t = next();
            if (tSelector.execute(t))
                return t;
        }
        return null;
    }

    default Iterator<T> first(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception {
        Objects.requireNonNull(runnable).run(first(tSelector));
        return this;
    }
    //endregion

    //region last
    default T last() throws Exception {
        final List<T> settle = settleList();
        return settle.size() > 0 ? settle.get(settle.size() - 1) : null;
    }

    default T last(ISelector<T> tSelector) throws Exception {
        final List<T> settle = settleList();
        T find = null;
        for (T t : settle)
            if (tSelector.execute(t))
                find = t;
        return find;
    }

    default Iterator<T> last(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception {
        runnable.run(last(tSelector));
        return this;
    }
    //endregion

    @Override
    default List<T> toList() throws Exception {
        return settleList();
    }

    @Override
    default java.util.Iterator<T> iterator() {
        try {
            return settleList().iterator();
        } catch (Exception e) {
            return Collections.EMPTY_LIST.iterator();
        }
    }
}
