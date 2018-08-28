/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery.common;


import sf.tquery.interfaces.Iterable;
import sf.tquery.interfaces.Iterator;
import sf.tquery.interfaces.exec.IAction;
import sf.tquery.interfaces.exec.IRunnable;
import sf.tquery.interfaces.exec.ISelector;
import sf.tquery.interfaces.exec.ITypeConverter;

import java.util.ArrayList;
import java.util.List;


class BaseIterator<T> implements Iterator<T>
{
	private Iterable<T> tIterable;
	private List<ISelector<T>> selectors;
	private T current;

	private BaseIterator()
	{
	}

	BaseIterator(T[] arr)
	{
		tIterable = new ArrayIterable<T>(arr);
	}

	BaseIterator(java.lang.Iterable<T> it)
	{
		tIterable = new JavaIterable<>(it);
	}

	@Override
	public <V> Iterator<V> as(ITypeConverter<T, V> tvTypeConverter) throws Exception
	{
		if (tvTypeConverter == null) throw new NullPointerException();
		final BaseIterator<V> objectBaseIterator = new BaseIterator<>();
		objectBaseIterator.tIterable = new AsIterable<>(tIterable, (ITypeConverter<Object, V>) tvTypeConverter);
		return objectBaseIterator;
	}

	@Override
	public Iterator<T> where(ISelector<T> tSelector) throws Exception
	{
		if (tSelector == null) throw new NullPointerException();
		if (selectors == null) selectors = new ArrayList<>();
		selectors.add(tSelector);
		return this;
	}

	@Override
	public Iterator<T> foreach(IRunnable<T> runnable) throws Exception
	{
		if (runnable == null) throw new NullPointerException();
		reset();
		while (hasNext())
			runnable.run(next());
		return this;
	}

	@Override
	public T first(ISelector<T> tSelector) throws Exception
	{
		if (tSelector == null) throw new NullPointerException();
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
		if (tSelector == null) throw new NullPointerException();
		if (runnable == null) throw new NullPointerException();
		reset();
		while (hasNext()) {
			T t = next();
			if (tSelector.execute(t)) {
				runnable.run(t);
				break;
			}
		}
		return this;
	}

	@Override
	public Iterator<T> add(T item) throws Exception
	{
		if (item != null)
			tIterable = new LinkedIterable<>(tIterable, new SingleItemIterable<>(item));
		return this;
	}

	@Override
	public Iterator<T> add(T[] arr) throws Exception
	{
		if (arr != null)
			tIterable = new LinkedIterable<>(tIterable, new ArrayIterable<>(arr));
		return this;
	}

	@Override
	public Iterator<T> add(java.lang.Iterable<T> it) throws Exception
	{
		if (it != null)
			tIterable = new LinkedIterable<>(tIterable, new JavaIterable<>(it));
		return this;
	}

	@Override
	public Iterator<T> add(Iterator<T> it) throws Exception
	{
		if (it != null)
			tIterable = new LinkedIterable<T>(tIterable, it);
		return this;
	}

	@Override
	public <V> V execute(IAction<V> action) throws Exception
	{
		if (action == null) throw new NullPointerException();
		return action.execute();
	}

	@Override
	public boolean hasNext() throws Exception
	{
		if (selectors != null) {
			while (tIterable.hasNext()) {
				final T t = tIterable.next();
				boolean pass = true;
				for (ISelector<T> selector : selectors)
					if (!selector.execute(t)) pass = false;
				if (pass) {
					current = t;
					return true;
				}
			}
			return false;
		} else {
			if (tIterable.hasNext()) {
				current = tIterable.next();
				return true;
			}
			return false;
		}
	}

	@Override
	public T next() throws Exception
	{
		return current;
	}

	@Override
	public void reset()
	{
		current = null;
		tIterable.reset();
	}

	@Override
	public List<T> toList() throws Exception
	{
		List<T> tList = new ArrayList<>();
		reset();
		while (hasNext())
			tList.add(next());
		return tList;
	}

}
