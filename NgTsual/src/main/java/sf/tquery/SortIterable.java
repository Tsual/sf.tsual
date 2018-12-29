package sf.tquery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class SortIterable<T> implements Iterable<T>, Iterable.SettledIterable<T> {
    private Iterable<T> inner_it;
    private boolean need_sort = true;
    private Comparator<? super T> comparator;

    SortIterable(Iterable<T> it, Comparator<? super T> comparator) {
        inner_it = it;
        this.comparator = comparator;
    }

    private void ensureSort() throws Exception {
        if (need_sort) {
            reset();
            List<T> list;
            if (inner_it instanceof SettledIterable)
                list = ((SettledIterable<T>) inner_it).settleList();
            else {
                list = new ArrayList<>();
                while (inner_it.hasNext())
                    list.add(inner_it.next());
            }
            list.sort(comparator);
            need_sort = false;
            inner_it = new JavaIterable<>(list);
        }
    }

    @Override
    public boolean hasNext() throws Exception {
        ensureSort();
        return inner_it.hasNext();
    }

    @Override
    public T next() throws Exception {
        ensureSort();
        return inner_it.next();
    }

    @Override
    public void reset() throws Exception {
        ensureSort();
        inner_it.reset();
    }

    @Override
    public List<T> settleList() throws Exception {
        ensureSort();
        return ((JavaIterable<T>) inner_it).settleList();
    }
}
