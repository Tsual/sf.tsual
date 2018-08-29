package sf.tquery.common;

import sf.tquery.interfaces.v2.Iterable;

import java.util.ArrayList;
import java.util.List;

class LinkedIterable<T> implements Iterable<T>
{
	static <T> Iterable<T> link(Iterable<T> pit, Iterable<T> ait)
	{
		if (pit instanceof LinkedIterable) {
			((LinkedIterable) pit).itList.add(ait);
			return pit;
		} else if (ait instanceof LinkedIterable) {
			((LinkedIterable) ait).itList.add(pit);
			return ait;
		} else {
			LinkedIterable<T> it = new LinkedIterable<T>();
			it.itList.add(pit);
			it.itList.add(ait);
			return it;
		}
	}

	private List<Iterable<T>> itList = new ArrayList<Iterable<T>>();
	int index = 0;

	private LinkedIterable()
	{

	}

	@Override
	public boolean hasNext() throws Exception
	{
		if (index < itList.size()) {
			final boolean it_has_next = itList.get(index).hasNext();
			if (!it_has_next) {
				if (index < itList.size() - 1) {
					while (true) {
						if (++index < itList.size()) {
							if (itList.get(index).hasNext())
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
		return itList.get(index).next();
	}

	@Override
	public void reset()
	{
		index = 0;
		for (Iterable t : itList)
			t.reset();
	}
}
