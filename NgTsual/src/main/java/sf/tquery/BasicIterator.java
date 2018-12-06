/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery;

import sf.tquery.irunshell.IAction;
import sf.tquery.irunshell.IRunnable;
import sf.tquery.irunshell.ISelector;
import sf.tquery.irunshell.ITypeConverter;
import sf.uds.util.ObjectHelper;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class BasicIterator<T> implements Iterator<T>
{
	private Iterable<T> tIterable;

	BasicIterator(T[] arr)
	{
		tIterable = new ArrayIterable<T>(arr);
	}

	BasicIterator(java.lang.Iterable<T> it)
	{
		tIterable = new JavaIterable<T>(it);
	}

	BasicIterator(Iterable<T> it)
	{
		tIterable = it;
	}

	@Override
	public <V> Iterator<V> as(ITypeConverter<T, V> tvTypeConverter)
	{
		return new BasicIterator<>(new AsIterable<>(tIterable, (ITypeConverter<Object, V>) ObjectHelper.requireNotNull(tvTypeConverter)));
	}

	@Override
	public <V> V execute(IAction<V> action) throws Exception
	{
		return ObjectHelper.requireNotNull(action).execute();
	}

	@Override
	public Iterator<T> sort(Comparator<? super T> comparator)
	{
		tIterable = new SortIterable<>(this, comparator);
		return this;
	}

	@Override
	public Iterator<T> where(ISelector<T> tSelector)
	{
		tIterable = WhereIterable.add(tIterable, ObjectHelper.requireNotNull(tSelector));
		return this;
	}

	@Override
	public Iterator<T> foreach(IRunnable<T> runnable) throws Exception
	{
		ObjectHelper.requireNotNull(runnable);
		reset();
		while (hasNext())
			runnable.run(next());
		return this;
	}

	//region first
	@Override
	public T first() throws Exception
	{
		reset();
		return hasNext() ? next() : null;
	}

	@Override
	public T first(ISelector<T> tSelector) throws Exception
	{
		ObjectHelper.requireNotNull(tSelector);
		reset();
		while (hasNext()) {
			T t = next();
			if (tSelector.execute(t))
				return t;
		}
		return null;
	}

	@Override
	public Iterator<T> first(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception
	{
		ObjectHelper.requireNotNull(runnable).run(first(tSelector));
		return this;
	}
	//endregion

	//region last
	@Override
	public T last() throws Exception
	{
		return last_inner(null, null);
	}

	@Override
	public T last(ISelector<T> tSelector) throws Exception
	{
		ObjectHelper.requireNotNull(tSelector);
		reset();
		return last_inner(tSelector, null);
	}

	@Override
	public Iterator<T> last(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception
	{
		ObjectHelper.requireNotNull(runnable).run(last(tSelector));
		return this;
	}

	private T last_inner(ISelector<T> tSelector, T pre_t) throws Exception
	{
		if (hasNext()) {
			final T next = next();
			if (tSelector != null && !tSelector.execute(next))
				return last_inner(tSelector, pre_t);
			else
				return last_inner(tSelector, next);
		} else {
			return pre_t;
		}
	}
	//endregion

	//region add
	@Override
	public Iterator<T> add(T item)
	{
		tIterable = LinkedIterable.link(tIterable, new SingleItemIterable<>(ObjectHelper.requireNotNull(item)));
		return this;
	}

	@Override
	public Iterator<T> add(T[] arr)
	{
		tIterable = LinkedIterable.link(tIterable, new ArrayIterable<>(ObjectHelper.requireNotNull(arr)));
		return this;
	}

	@Override
	public Iterator<T> add(java.lang.Iterable<T> it)
	{
		tIterable = LinkedIterable.link(tIterable, new JavaIterable<>(ObjectHelper.requireNotNull(it)));
		return this;
	}

	@Override
	public Iterator<T> add(Iterable<T> it)
	{
		tIterable = LinkedIterable.link(tIterable, ObjectHelper.requireNotNull(it));
		return this;
	}
	//endregion

	//region Iterable
	@Override
	public boolean hasNext() throws Exception
	{
		return tIterable.hasNext();
	}

	@Override
	public T next() throws Exception
	{
		return tIterable.next();
	}

	@Override
	public void reset() throws Exception
	{
		tIterable.reset();
	}
	//endregion

	@Override
	public List<T> toList() throws Exception
	{
		List<T> tList = new LinkedList<>();
		reset();
		while (hasNext())
			tList.add(next());
		return tList;
	}

}
