package sf.tquery.common;

import sf.tquery.interfaces.Iterator;

import java.util.ArrayList;

public class Iterators
{
	public static <T> Iterator<T> get(java.lang.Iterable<T> it)
	{
		if (it == null)
			it=new ArrayList<T>();
		return new BaseIterator<T>(it);
	}

	public static <T> Iterator<T> get(T[] ar)
	{
		if (ar == null)
			return get(new ArrayList<T>());
		return new BaseIterator<T>(ar);
	}


}
