package sf.tquery;

import java.util.Arrays;
import java.util.List;

public class ArrayIterable<T> implements Iterable<T> {
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
    public List<T> settle() {
        if (settle_object == null)
            settle_object = Arrays.asList(arr);
        return settle_object;
    }
}
