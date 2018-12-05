package sf.tquery;

class ArrayIterable<T> implements Iterable<T>
{
	private T[] arr;
	private int index = -1;

	ArrayIterable(T[] arr)
	{
		this.arr = arr;
	}

	@Override
	public boolean hasNext()
	{
		return ++index < arr.length;
	}

	@Override
	public T next() throws Exception
	{
		return arr[index];
	}

	@Override
	public void reset()
	{
		index = -1;
	}
}
