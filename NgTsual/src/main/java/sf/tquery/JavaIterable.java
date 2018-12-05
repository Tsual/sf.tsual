package sf.tquery;

import java.util.Iterator;

class JavaIterable<T> implements Iterable<T>
{
	private java.lang.Iterable<T> it;
	private Iterator<T> ito;

	JavaIterable(java.lang.Iterable<T> it)
	{
		this.it = it;
	}

	@Override
	public boolean hasNext()
	{
		if (ito == null) ito = it.iterator();
		return ito.hasNext();
	}

	@Override
	public T next() throws Exception
	{
		return ito.next();
	}

	@Override
	public void reset()
	{
		ito=it.iterator();
	}
}
