/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery;

import sf.tquery.irunshell.ISelector;
import sf.tquery.irunshell.ITypeConverter;
import sf.uds.common.Iterable;
import sf.util.ObjectHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class BasicIterator<T> implements Iterator<T> {
    private Iterable<T> tIterable;
    private List<T> settledList;

    BasicIterator(T[] arr) {
        tIterable = new ArrayIterable<>(arr);
    }

    BasicIterator(java.lang.Iterable<T> it) {
        tIterable = new JavaIterable<>(it);
    }

    BasicIterator(Iterable<T> it) {
        tIterable = it;
    }

    @Override
    public <V> Iterator<V> as(ITypeConverter<T, V> tvTypeConverter) {
        return new BasicIterator<>(new AsIterable<>(tIterable, Objects.requireNonNull(tvTypeConverter)));
    }

    @Override
    public <V> Iterator<V> map(ITypeConverter<T, Iterable<V>> tvTypeConverter) {
        return new BasicIterator<>(new MapIterable<>(tIterable, Objects.requireNonNull(tvTypeConverter)));
    }

    @Override
    public Iterator<T> sort(Comparator<? super T> comparator) {
        tIterable = new SortIterable<>(this, comparator);
        return this;
    }

    @Override
    public Iterator<T> where(ISelector<T> tSelector) {
        tIterable = WhereIterable.add(tIterable, Objects.requireNonNull(tSelector));
        return this;
    }

    @Override
    public Iterator<T> add(T item) {
        tIterable = LinkedIterable.link(tIterable, new SingleItemIterable<>(Objects.requireNonNull(item)));
        return this;
    }

    @Override
    public Iterator<T> add(T[] arr) {
        tIterable = LinkedIterable.link(tIterable, new ArrayIterable<>(Objects.requireNonNull(arr)));
        return this;
    }

    @Override
    public Iterator<T> add(java.lang.Iterable<T> it) {
        tIterable = LinkedIterable.link(tIterable, new JavaIterable<>(Objects.requireNonNull(it)));
        return this;
    }

    @Override
    public Iterator<T> add(Iterable<T> it) {
        tIterable = LinkedIterable.link(tIterable, Objects.requireNonNull(it));
        return this;
    }

    @Override
    public boolean hasNext() throws Exception {
        return tIterable.hasNext();
    }

    @Override
    public T next() throws Exception {
        return tIterable.next();
    }

    @Override
    public void reset() throws Exception {
        tIterable.reset();
    }

    @Override
    public List<T> settleList() throws Exception {
        if (settledList == null) {
            settledList = new ArrayList<>();
            while (hasNext())
                settledList.add(next());
        }
        return settledList;
    }
}
