package sf.tquery;

import sf.tquery.irunshell.ITypeConverter;
import sf.uds.common.Iterable;

class AsIterable<T,F> implements Iterable<T> {
    private Iterable<F> it;
    private ITypeConverter<F, T> converter;

    AsIterable(Iterable it, ITypeConverter<F, T> converter) {
        this.it = it;
        this.converter = converter;
    }

    @Override
    public boolean hasNext() throws Exception {
        return it.hasNext();
    }

    @Override
    public T next() throws Exception {
        return converter.execute(it.next());
    }

    @Override
    public void reset() throws Exception {
        it.reset();
    }
}
