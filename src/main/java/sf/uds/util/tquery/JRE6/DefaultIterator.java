/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.util.tquery.JRE6;


import sf.uds.interfaces.ctx.IBaseContext;
import sf.uds.util.tquery.interfaces.exec.*;

import java.util.*;

class DefaultIterator<T> extends VirtualIterator<T> implements sf.uds.util.tquery.interfaces.Iterator<T>
{
	DefaultIterator(Iterable<T> it)
	{
		_InnerIt = it;
	}

	DefaultIterator(T[] ar)
	{
		_InnerIt = Arrays.asList(ar);
	}

	DefaultIterator(Iterable<T> it, Map ctx)
	{
		_InnerIt = it;
		_Context = ctx;
	}

	DefaultIterator(T[] ar, Map ctx)
	{
		_InnerIt = Arrays.asList(ar);
		_Context = ctx;
	}

	@Override
	public List<T> toList()
	{
		List<T> res = new ArrayList<>();
		for (T t : toIterable())
			res.add(t);
		return res;
	}

	@Override
	public Iterable<T> toIterable()
	{
		if (_InnerIt == null) return null;
		return () ->
		{
			final Iterator<T> _InnerIterator = _InnerIt.iterator();
			return new Iterator<T>()
			{
				private T _Current;

				private boolean combineVerify(T t) throws Exception
				{
					if (_InnerSelectors != null)
						for (ISelector<T> s : _InnerSelectors)
							if (!s.execute(t))
								return false;
					return true;
				}

				@Override
				public void remove()
				{
					_InnerIterator.remove();
				}

				@Override
				public boolean hasNext()
				{
					while (_InnerIterator.hasNext()) {
						T t = _InnerIterator.next();
						try {
							if (combineVerify(t)) {
								_Current = t;
								return true;
							}
						} catch (Exception e) {
							return false;
						}
					}
					return false;
				}

				@Override
				public T next()
				{
					return _Current;
				}
			};
		};
	}

	@Override
	public <V> sf.uds.util.tquery.interfaces.Iterator<V> as(final ITypeConverter<T, V> tvTypeConverter)
	{
		if (_InnerIt == null) return null;
		if (tvTypeConverter instanceof IBaseContext)
			this.vauExecutable((IBaseContext) tvTypeConverter);
		return new DefaultIterator<>(() ->
		{
			final Iterator<T> _InnerIterator = _InnerIt.iterator();
			return new Iterator<V>()
			{
				private T _Current;

				private boolean combineVerify(T t) throws Exception
				{
					if (_InnerSelectors != null)
						for (ISelector<T> s : _InnerSelectors)
							if (!s.execute(t))
								return false;
					return true;
				}

				@Override
				public boolean hasNext()
				{
					try {
						while (_InnerIterator.hasNext()) {
							T t = _InnerIterator.next();
							if (combineVerify(t)) {
								_Current = t;
								return true;
							}
						}
					} catch (Exception e) {
						return false;
					}
					return false;
				}

				@Override
				public V next()
				{
					try {
						return tvTypeConverter.execute(_Current);
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}

				@Override
				public void remove()
				{
					_InnerIterator.remove();
				}
			};
		}, _Context);
	}

	@Override
	public sf.uds.util.tquery.interfaces.Iterator<T> where(ISelector<T> tSelector)
	{
		if (tSelector instanceof IBaseContext)
			this.vauExecutable((IBaseContext) tSelector);
		if (_InnerSelectors == null)
			_InnerSelectors = new ArrayList<>();
		_InnerSelectors.add(tSelector);
		return this;
	}

	@Override
	public sf.uds.util.tquery.interfaces.Iterator<T> any(ISelector<T> tSelector, IRunnable<sf.uds.util.tquery.interfaces.Iterator<T>> runnable) throws Exception
	{
		if (tSelector instanceof IBaseContext)
			this.vauExecutable((IBaseContext) tSelector);
		if (runnable instanceof IBaseContext)
			this.vauExecutable((IBaseContext) runnable);
		if (tSelector == null || runnable == null) return null;
		List<T> res = new ArrayList<>();
		for (T t : toIterable())
			res.add(t);
		runnable.run(new DefaultIterator<>(res));
		return this;
	}

	@Override
	public T first()
	{
		final Iterator<T> iterator = toIterable().iterator();
		if (iterator.hasNext())
			return iterator.next();
		return null;
	}

	@Override
	public T first(ISelector<T> tSelector) throws Exception
	{
		if (tSelector instanceof IBaseContext)

			this.vauExecutable((IBaseContext) tSelector);
		for (T t : toIterable())
			if (tSelector.execute(t))
				return t;
		return null;
	}

	@Override
	public sf.uds.util.tquery.interfaces.Iterator<T> first(ISelector<T> tSelector, IRunnable<T> del) throws Exception
	{
		if (tSelector instanceof IBaseContext)
			this.vauExecutable((IBaseContext) tSelector);
		if (del instanceof IBaseContext)
			this.vauExecutable((IBaseContext) del);
		T t = first(tSelector);
		if (del != null)
			del.run(t);
		return this;
	}

	@Override
	public sf.uds.util.tquery.interfaces.Iterator<T> foreach(IRunnable<T> del) throws Exception
	{
		if (del instanceof IBaseContext)
			this.vauExecutable((IBaseContext) del);
		for (T t : this.toIterable())
			del.run(t);
		return this;
	}

	@Override
	public sf.uds.util.tquery.interfaces.Iterator<T> executeWithList(IRunnable<List<T>> del) throws Exception
	{
		if (del instanceof IBaseContext)
			this.vauExecutable((IBaseContext) del);
		if (del != null)
			del.run(this.toList());
		return this;
	}

	@Override
	public sf.uds.util.tquery.interfaces.Iterator<T> executeWithIterator(IRunnable<sf.uds.util.tquery.interfaces.Iterator<T>> del) throws Exception
	{
		if (del instanceof IBaseContext)
			this.vauExecutable((IBaseContext) del);
		if (del != null)
			del.run(this);
		return this;
	}

	@Override
	public sf.uds.util.tquery.interfaces.Iterator<T> context(IRunnable<Map> del) throws Exception
	{
		if (del instanceof IBaseContext)
			this.vauExecutable((IBaseContext) del);
		if (del != null)
			del.run(_Context);
		return this;
	}

	@Override
	public <V> sf.uds.util.tquery.interfaces.Iterator<V> reset(Class<V> vClass, ITypeConverter<sf.uds.util.tquery.interfaces.Iterator, sf.uds.util.tquery.interfaces.Iterator<V>> failConverter) throws Exception
	{
		_InnerSelectors = null;
		while (_InnerIt instanceof DefaultIterator && ((DefaultIterator) _InnerIt)._InnerIt instanceof DefaultIterator)
			_InnerIt = ((DefaultIterator) _InnerIt)._InnerIt;
		try {
			return (sf.uds.util.tquery.interfaces.Iterator<V>) ((DefaultIterator) _InnerIt)._InnerIt;
		} catch (ClassCastException ex) {
			if (failConverter != null) {
				return failConverter.execute((sf.uds.util.tquery.interfaces.Iterator) ((DefaultIterator) _InnerIt)._InnerIt);
			} else {
				throw ex;
			}
		}
	}

	@Override
	public <K, V> EntryIterator<Map.Entry<K, V>> toEntryIterator(ITypeConverter<T, Map.Entry<K, V>> typeConverter) throws Exception
	{
		if (typeConverter instanceof IBaseContext)
			this.vauExecutable((IBaseContext) typeConverter);
		if (typeConverter != null)
			return new EntryIterator<>(this.as(typeConverter).toList(), _Context);
		return null;
	}

	@Override
	public <V> V cast(ITypeConverter<sf.uds.util.tquery.interfaces.Iterator<T>, V> failConverter) throws Exception
	{
		try {
			return (V) this;
		} catch (ClassCastException ex) {
			return failConverter == null ? null : failConverter.execute(this);
		}
	}

	@Override
	public <V> V execute(IAction<V> action) throws Exception
	{
		if (action instanceof IBaseContext)
			this.vauExecutable((IBaseContext) action);
		if (action != null)
			return action.execute();
		return null;
	}


	@Override
	public <V> sf.uds.util.tquery.interfaces.Iterator<V> convert(ITypeConverter<T, V> tvTypeConverter) throws Exception
	{
		if (tvTypeConverter instanceof IBaseContext)
			this.vauExecutable((IBaseContext) tvTypeConverter);
		List<V> list = new ArrayList<V>();
		for (T t : toIterable())
			list.add(tvTypeConverter.execute(t));
		return new DefaultIterator<V>(list, _Context);
	}

	@Override
	public Iterator<T> iterator()
	{
		return this.toIterable().iterator();
	}


}
