package sf.tquery;

class SingleItemIterable<T> implements Iterable<T>
{
	private T item;
	private boolean isDo = true;

	SingleItemIterable(T item)
	{
		this.item = item;
	}

	@Override
	public boolean hasNext()
	{
		return isDo && !(isDo = false);
	}

	@Override
	public T next()
	{
		return item;
	}

	@Override
	public void reset()
	{
		isDo = true;
	}
}
