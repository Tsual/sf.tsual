package sf.tquery;

import sf.tquery.irunshell.IAction;
import sf.tquery.irunshell.IRunnable;
import sf.tquery.irunshell.ISelector;
import sf.tquery.irunshell.ITypeConverter;
import sf.uds.interfaces.common.Listable;

import java.util.Comparator;

public interface Iterator<T> extends Iterable<T>, Listable<T>
{
	Iterator<T> foreach(IRunnable<T> runnable) throws Exception;
	<V> Iterator<V> as(ITypeConverter<T, V> tvTypeConverter);
	Iterator<T> sort(Comparator<? super T> comparator);
	Iterator<T> where(ISelector<T> tSelector);
	<V> V execute(IAction<V> action) throws Exception;

	//region add
	Iterator<T> add(T item) throws Exception;
	Iterator<T> add(T[] array) throws Exception;
	Iterator<T> add(java.lang.Iterable<T> Iterable) throws Exception;
	Iterator<T> add(Iterable<T> Iterator) throws Exception;
	//endregion

	//region first
	T first() throws Exception;
	T first(ISelector<T> tSelector) throws Exception;
	Iterator<T> first(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception;
	//endregion

	//region last
	T last() throws Exception;
	T last(ISelector<T> tSelector) throws Exception;
	Iterator<T> last(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception;
	//endregion
}
