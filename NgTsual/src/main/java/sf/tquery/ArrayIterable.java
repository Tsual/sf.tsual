package sf.tquery;

import sf.uds.common.Iterable;
import sf.uds.common.SettledIterable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayIterable<T> implements Iterable<T>, SettledIterable<T>, java.lang.Iterable<T> {
    private T[] arr;
    private int index = -1;
    private List<T> settle_object;

    public ArrayIterable(T[] arr) {
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        return ++index < arr.length;
    }

    @Override
    public T next() {
        return arr[index];
    }

    @Override
    public void reset() {
        index = -1;
    }

    @Override
    public List<T> settleList() {
        if (settle_object == null)
            settle_object = Arrays.asList(arr);
        return settle_object;
    }

    @Override
    public Iterator<T> iterator() {
        return settleList().iterator();
    }
}
