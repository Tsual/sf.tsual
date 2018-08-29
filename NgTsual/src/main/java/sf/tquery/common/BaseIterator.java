/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery.common;


import sf.tquery.interfaces.v2.Iterable;
import sf.tquery.interfaces.v2.Iterator;
import sf.tquery.interfaces.exec.IAction;
import sf.tquery.interfaces.exec.IRunnable;
import sf.tquery.interfaces.exec.ISelector;
import sf.tquery.interfaces.exec.ITypeConverter;
import sf.uds.util.ObjectHelper;

import java.util.ArrayList;
import java.util.List;


class BaseIterator<T> implements Iterator<T>
{
	private Iterable<T> tIterable;

	private BaseIterator()
	{
	}

	BaseIterator(T[] arr)
	{
		tIterable = new ArrayIterable<T>(arr);
	}

	BaseIterator(java.lang.Iterable<T> it)
	{
		tIterable = new JavaIterable<T>(it);
	}

	private BaseIterator(Iterable<T> it)
	{
		tIterable = it;
	}

	@Override
	public <V> Iterator<V> as(ITypeConverter<T, V> tvTypeConverter)
	{
		return new BaseIterator<V>(new AsIterable<V>(tIterable, (ITypeConverter<Object, V>) ObjectHelper.requireNotNull(tvTypeConverter)));
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

	@Override
	public T last(ISelector<T> tSelector) throws Exception
	{
		ObjectHelper.requireNotNull(tSelector);
		reset();
		T t = null;
		while (hasNext()) {
			T t1 = next();
			if (tSelector.execute(t1))
				t = t1;
		}
		return t;
	}

	@Override
	public Iterator<T> last(ISelector<T> tSelector, IRunnable<T> runnable) throws Exception
	{
		ObjectHelper.requireNotNull(runnable).run(last(tSelector));
		return this;
	}

	@Override
	public Iterator<T> settle() throws Exception
	{
		tIterable = new JavaIterable<T>(toList());
		return this;
	}

	@Override
	public Iterator<T> add(T item)
	{
		tIterable = LinkedIterable.link(tIterable, new SingleItemIterable<T>(ObjectHelper.requireNotNull(item)));
		return this;
	}

	@Override
	public Iterator<T> add(T[] arr)
	{
		tIterable = LinkedIterable.link(tIterable, new ArrayIterable<T>(ObjectHelper.requireNotNull(arr)));
		return this;
	}

	@Override
	public Iterator<T> add(java.lang.Iterable<T> it)
	{
		tIterable = LinkedIterable.link(tIterable, new JavaIterable<T>(ObjectHelper.requireNotNull(it)));
		return this;
	}

	@Override
	public Iterator<T> add(Iterable<T> it)
	{
		tIterable = LinkedIterable.link(tIterable, ObjectHelper.requireNotNull(it));
		return this;
	}

	@Override
	public <V> V execute(IAction<V> action) throws Exception
	{
		return ObjectHelper.requireNotNull(action).execute();
	}

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
	public void reset()
	{
		tIterable.reset();
	}

	@Override
	public List<T> toList() throws Exception
	{
		List<T> tList = new ArrayList<T>();
		reset();
		while (hasNext())
			tList.add(next());
		return tList;
	}

}
