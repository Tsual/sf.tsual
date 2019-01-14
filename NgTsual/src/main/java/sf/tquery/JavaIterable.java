package sf.tquery;

import sf.uds.common.Iterable;
import sf.uds.common.SettledIterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JavaIterable<T> implements Iterable<T>, SettledIterable<T>, java.lang.Iterable<T> {
    private java.lang.Iterable<T> it;
    private Iterator<T> ito;
    private List settledList;

    public JavaIterable(java.lang.Iterable<T> it) {
        this.it = it;
    }

    @Override
    public boolean hasNext() {
        if (ito == null) ito = it.iterator();
        return ito.hasNext();
    }

    @Override
    public T next() {
        return ito.next();
    }

    @Override
    public void reset() {
        ito = it.iterator();
    }

    @Override
    public List<T> settleList() throws Exception {
        if (it instanceof List)
            return (List<T>) it;
        else {
            if (settledList == null) {
                settledList = new ArrayList();
                while (hasNext())
                    settledList.add(next());
            }
            return settledList;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return it.iterator();
    }
}
