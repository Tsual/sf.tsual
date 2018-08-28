package sf.tquery.interfaces;

import sf.tquery.interfaces.exec.IAction;
import sf.tquery.interfaces.exec.IRunnable;
import sf.tquery.interfaces.exec.ISelector;
import sf.tquery.interfaces.exec.ITypeConverter;
import sf.uds.interfaces.util.list.Listable;

public interface Iterator<T> extends Iterable<T>, Listable<T>
{
	<V> Iterator<V> as(ITypeConverter<T, V> tvTypeConverter) throws Exception;

	<V> Iterator<V> convert(ITypeConverter<T, V> tvTypeConverter) throws Exception;

	Iterator<T> where(ISelector<T> tSelector) throws Exception;

	Iterator<T> foreach(IRunnable<T> runnable) throws Exception;

	T first(ISelector<T> tSelector) throws Exception;

	Iterator<T> first(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception;

	Iterator<T> add(T item) throws Exception;

	Iterator<T> add(T[] array) throws Exception;

	Iterator<T> add(java.lang.Iterable<T> Iterable) throws Exception;

	Iterator<T> add(Iterable<T> Iterator) throws Exception;

	<V> V execute(IAction<V> action) throws Exception;
}
