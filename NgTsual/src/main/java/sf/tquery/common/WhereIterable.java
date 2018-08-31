package sf.tquery.common;

import sf.tquery.interfaces.exec.ISelector;

import java.util.ArrayList;
import java.util.List;

class WhereIterable<T> implements Iterable<T>
{
	static <T> Iterable<T> add(Iterable<T> ori, ISelector<T> selector)
	{
		if (ori instanceof WhereIterable) {
			((WhereIterable) ori).selectors.add(selector);
			return ori;
		} else {
			WhereIterable<T> ins = new WhereIterable<T>();
			ins.selectors.add(selector);
			ins.ori=ori;
			return ins;
		}
	}

	private List<ISelector<T>> selectors = new ArrayList<ISelector<T>>();
	private Iterable<T> ori;
	private T current;

	private WhereIterable()
	{
	}

	@Override
	public boolean hasNext() throws Exception
	{
		if (selectors != null) {
			while (ori.hasNext()) {
				final T t = ori.next();
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
			if (ori.hasNext()) {
				current = ori.next();
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
	public void reset() throws Exception
	{
		current = null;
		ori.reset();
	}
}
