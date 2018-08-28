package sf.tquery.common;

import sf.tquery.interfaces.v2.Iterable;

class LinkedIterable<T> implements Iterable<T>
{
	private Iterable<T> pit;
	private Iterable<T> ait;
	private boolean bf = true;

	LinkedIterable(Iterable<T> pit, Iterable<T> ait)
	{
		this.pit = pit;
		this.ait = ait;
	}

	@Override
	public boolean hasNext() throws Exception
	{
		if (bf) {
			if (pit.hasNext()) {
				return true;
			} else {
				bf = false;
				return ait.hasNext();
			}
		} else {
			return ait.hasNext();
		}
	}

	@Override
	public T next() throws Exception
	{
		return bf ? pit.next() : ait.next();
	}

	@Override
	public void reset()
	{
		pit.reset();
		ait.reset();
		bf = true;
	}
}
