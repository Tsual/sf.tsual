/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery.common;

import sf.tquery.interfaces.exec.IRunnable;

import java.util.*;

public abstract class AbstractIterator<T> implements sf.tquery.interfaces.preview.Iterator<T>
{
	private Map<T, List<Iterable<T>>> iterableMap = new HashMap<T, List<Iterable<T>>>();

	protected AbstractIterator(Iterable<T> basicIterable)
	{
		iterableMap.put(null, Arrays.asList(basicIterable));
	}

	protected void appendIterable(T obj, Iterable<T> iterable)
	{
		List<Iterable<T>> list = iterableMap.get(obj);
		if (list == null)
			iterableMap.put(obj, Arrays.asList(iterable));
		else
			list.add(iterable);
	}

	@Override
	public sf.tquery.interfaces.preview.Iterator<T> foreach(IRunnable<T> del) throws Exception
	{
		for (T t : this)
			del.run(t);
		return this;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new Iterator<T>()
		{
			Iterator<T> current = iterableMap.get(null).get(0).iterator();
			Stack<Iterator<T>> before = new Stack<Iterator<T>>();

			@Override
			public boolean hasNext()
			{
				Boolean curFlag = current.hasNext();
				if (curFlag) return true;

				while (!(current = before.pop()).hasNext())
				{

				}
				return false;
			}

			@Override
			public T next()
			{
				return null;
			}

			@Override
			public void remove()
			{

			}
		};
	}
}
