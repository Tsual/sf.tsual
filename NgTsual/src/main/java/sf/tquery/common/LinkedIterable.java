package sf.tquery.common;

import sf.tquery.interfaces.v2.Iterable;

import java.util.ArrayList;
import java.util.List;

class LinkedIterable<T> implements Iterable<T>
{
	static <T> Iterable<T> link(Iterable<T> pit, Iterable<T> ait)
	{
		if (pit instanceof LinkedIterable) {
			((LinkedIterable) pit).iterables.add(ait);
			return pit;
		} else if (ait instanceof LinkedIterable) {
			((LinkedIterable) ait).iterables.add(pit);
			return ait;
		} else {
			LinkedIterable<T> it = new LinkedIterable<T>();
			it.iterables.add(pit);
			it.iterables.add(ait);
			return it;
		}
	}

	private List<Iterable<T>> iterables = new ArrayList<Iterable<T>>();
	private int index = 0;

	private LinkedIterable()
	{

	}

	@Override
	public boolean hasNext() throws Exception
	{
		if (index < iterables.size()) {
			final boolean it_has_next = iterables.get(index).hasNext();
			if (!it_has_next) {
				if (index < iterables.size() - 1) {
					while (true) {
						if (++index < iterables.size()) {
							if (iterables.get(index).hasNext())
								return true;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public T next() throws Exception
	{
		return iterables.get(index).next();
	}

	@Override
	public void reset()
	{
		index = 0;
		for (Iterable t : iterables)
			t.reset();
	}
}
