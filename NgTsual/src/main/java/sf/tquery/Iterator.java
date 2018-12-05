package sf.tquery;

import sf.tquery.interfaces.IAction;
import sf.tquery.interfaces.IRunnable;
import sf.tquery.interfaces.ISelector;
import sf.tquery.interfaces.ITypeConverter;
import sf.uds.interfaces.common.Listable;

public interface Iterator<T> extends Iterable<T>, Listable<T>
{
	Iterator<T> foreach(IRunnable<T> runnable) throws Exception;

	Iterator<T> settle() throws Exception;

	Iterator<T> add(T item) throws Exception;

	Iterator<T> add(T[] array) throws Exception;

	Iterator<T> add(java.lang.Iterable<T> Iterable) throws Exception;

	Iterator<T> add(Iterable<T> Iterator) throws Exception;

	<V> Iterator<V> as(ITypeConverter<T, V> tvTypeConverter) throws Exception;

	Iterator<T> where(ISelector<T> tSelector) throws Exception;

	<V> V execute(IAction<V> action) throws Exception;

	T first(ISelector<T> tSelector) throws Exception;

	Iterator<T> first(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception;

	T last(ISelector<T> tSelector) throws Exception;

	Iterator<T> last(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception;
}
