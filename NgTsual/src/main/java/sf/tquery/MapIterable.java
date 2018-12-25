package sf.tquery;

import sf.tquery.irunshell.ITypeConverter;

class MapIterable<T> implements Iterable<T> {
    private final Iterable it;
    private final ITypeConverter<Object, Iterable<T>> tvTypeConverter;
    private Iterable<T> t_obj;

    MapIterable(Iterable it, ITypeConverter<Object, Iterable<T>> tvTypeConverter) {
        this.it = it;
        this.tvTypeConverter = tvTypeConverter;
    }

    @Override
    public boolean hasNext() throws Exception {
        if (t_obj != null && t_obj.hasNext()) return true;
        else if (it.hasNext()) {
            t_obj = tvTypeConverter.execute(it.next());
            return hasNext();
        } else return false;
    }

    @Override
    public T next() throws Exception {
        return t_obj.next();
    }

    @Override
    public void reset() throws Exception {
        t_obj = null;
        it.reset();
    }
}
