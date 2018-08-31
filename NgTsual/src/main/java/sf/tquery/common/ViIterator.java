package sf.tquery.common;

import sf.tquery.interfaces.exec.IAction;
import sf.tquery.interfaces.exec.IRunnable;
import sf.tquery.interfaces.exec.ISelector;
import sf.tquery.interfaces.exec.ITypeConverter;

interface ViIterator<T>
{
	ViIterator<T> add(T item) throws Exception;

	ViIterator<T> add(T[] array) throws Exception;

	ViIterator<T> add(java.lang.Iterable<T> Iterable) throws Exception;

	ViIterator<T> add(Iterable<T> Iterator) throws Exception;

	<V> ViIterator<V> as(ITypeConverter<T, V> tvTypeConverter) throws Exception;

	ViIterator<T> where(ISelector<T> tSelector) throws Exception;

	<V> V execute(IAction<V> action) throws Exception;

	T first(ISelector<T> tSelector) throws Exception;

	ViIterator<T> first(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception;

	T last(ISelector<T> tSelector) throws Exception;

	ViIterator<T> last(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception;
}
