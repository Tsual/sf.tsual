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
import sf.uds.util.ObjectHelper;

import java.util.Comparator;

class BasicIterator<T> implements Iterator<T> {
    private Iterable<T> tIterable;

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
        return new BasicIterator<>(new AsIterable<>(tIterable, (ITypeConverter<Object, V>) ObjectHelper.requireNotNull(tvTypeConverter)));
    }

    @Override
    public <V> Iterator<V> map(ITypeConverter<T, Iterable<V>> tvTypeConverter) {
        return new BasicIterator<>(new MapIterable<>(tIterable, (ITypeConverter<Object, Iterable<V>>) ObjectHelper.requireNotNull(tvTypeConverter)));
    }

    @Override
    public Iterator<T> sort(Comparator<? super T> comparator) {
        tIterable = new SortIterable<>(this, comparator);
        return this;
    }

    @Override
    public Iterator<T> where(ISelector<T> tSelector) {
        tIterable = WhereIterable.add(tIterable, ObjectHelper.requireNotNull(tSelector));
        return this;
    }

    @Override
    public Iterator<T> add(T item) {
        tIterable = LinkedIterable.link(tIterable, new SingleItemIterable<>(ObjectHelper.requireNotNull(item)));
        return this;
    }

    @Override
    public Iterator<T> add(T[] arr) {
        tIterable = LinkedIterable.link(tIterable, new ArrayIterable<>(ObjectHelper.requireNotNull(arr)));
        return this;
    }

    @Override
    public Iterator<T> add(java.lang.Iterable<T> it) {
        tIterable = LinkedIterable.link(tIterable, new JavaIterable<>(ObjectHelper.requireNotNull(it)));
        return this;
    }

    @Override
    public Iterator<T> add(Iterable<T> it) {
        tIterable = LinkedIterable.link(tIterable, ObjectHelper.requireNotNull(it));
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
}
