package sf.tquery.common;

import sf.tquery.interfaces.exec.ITypeConverter;

class AsIterable<T> implements Iterable<T>
{
	private Iterable it;
	private ITypeConverter<Object, T> converter;

	AsIterable(Iterable it, ITypeConverter<Object, T> converter)
	{
		this.it = it;
		this.converter = converter;
	}

	@Override
	public boolean hasNext() throws Exception
	{
		return it.hasNext();
	}

	@Override
	public T next() throws Exception
	{
		return converter.execute(it.next());
	}

	@Override
	public void reset() throws Exception
	{
		it.reset();
	}
}
