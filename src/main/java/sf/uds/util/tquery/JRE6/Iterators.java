package sf.uds.util.tquery.JRE6;

import sf.uds.util.beans.EntryBean;
import sf.uds.util.tquery.JRE6.exec.Binder;
import sf.uds.util.tquery.interfaces.Iterator;

import java.util.ArrayList;
import java.util.Map;

public class Iterators
{
	public static <T> Iterator<T> getIterator(Iterable<T> it)
	{
		if (it == null)
			return new DefaultIterator<T>(new ArrayList<T>());
		return new DefaultIterator<T>(it);
	}

	public static <T> Iterator<T> getIterator(T[] ar)
	{
		if (ar == null)
			return new DefaultIterator<T>(new ArrayList<T>());
		return new DefaultIterator<T>(ar);
	}

	public static <T> Iterator<T> getIterator(Map map)
	{
		if (map == null)
			return new DefaultIterator<T>(new ArrayList<T>());
		return new EntryIterator(map.entrySet());
	}

	public static <K, V> Iterator<Map.Entry<K, V>> getIterator(Iterable<K> it1, Iterable<V> it2)
	{
		ArrayList<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>();
		if (it1 == null || it2 == null)
			return new DefaultIterator<Map.Entry<K, V>>(list);

		java.util.Iterator<K> it1t = it1.iterator();
		java.util.Iterator<V> it2t = it2.iterator();

		while (true) {
			boolean it1next = it1t.hasNext();
			boolean it2next = it2t.hasNext();
			if ((it1next | it1next) ^ true)
				break;
			list.add(new EntryBean<K, V>(it1next ? it1t.next() : null, it2next ? it2t.next() : null));
		}

		return new DefaultIterator<Map.Entry<K, V>>(list);
	}

	public static <K, V> Iterator<? extends Map.Entry<K, V>> getIterator(Iterable<K> it1, Iterable<V> it2, Binder<? extends Iterable<Map.Entry<K, V>>, java.util.Iterator<K>, java.util.Iterator<V>> binder) throws Exception
	{
		ArrayList<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>();
		if (it1 == null | it2 == null | binder == null)
			return new DefaultIterator<Map.Entry<K, V>>(list);
		return new DefaultIterator<Map.Entry<K, V>>(binder.execute(it1.iterator(), it2.iterator()));
	}
}
