package sf.tquery.common;

import sf.tquery.interfaces.v2.Iterator;
import sf.uds.util.ObjectHelper;

public class Iterators
{
	public static <T> Iterator<T> basic(java.lang.Iterable<T> it)
	{
		return new BasicIterator<T>(ObjectHelper.requireNotNull(it));
	}

	public static <T> Iterator<T> basic(T[] ar)
	{
		return new BasicIterator<T>(ObjectHelper.requireNotNull(ar));
	}


}
