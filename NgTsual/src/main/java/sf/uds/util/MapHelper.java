package sf.uds.util;

import sf.uds.util.beans.EntryBean;

import java.util.*;

public class MapHelper
{
	public static <K, V> Map<K, V> getMapWithEntry(Map.Entry<K, V>... entries)
	{
		Map<K, V> map = new HashMap<K, V>();
		for (Map.Entry<K, V> entry : entries)
			map.put(entry.getKey(), entry.getValue());
		return map;
	}

	public static <K, V> Map<K, V> getMap(K[] keyIt, V[] valueIt)
	{
		Map<K, V> map = new HashMap<K, V>();
		for (int i = 0; i < keyIt.length; i++) {
			map.put(keyIt[i], i < valueIt.length ? valueIt[i] : null);
		}
		return map;
	}

	public static <K, V> Map<K, V> getMap(java.lang.Iterable<K> keyIt, java.lang.Iterable<V> valueIt)
	{
		Map<K, V> map = new HashMap<K, V>();
		final Iterator<K> kIterator = keyIt.iterator();
		final Iterator<V> vIterator = valueIt.iterator();
		while (kIterator.hasNext()) {
			if (vIterator.hasNext()) {
				map.put(kIterator.next(), vIterator.next());
			} else {
				map.put(kIterator.next(), null);
			}
		}
		return map;
	}

	public static <K, V> List<Map.Entry<K, V>> getEntryList(java.lang.Iterable<K> keyIt, java.lang.Iterable<V> valueIt)
	{
		List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>();
		final Iterator<K> kIterator = keyIt.iterator();
		final Iterator<V> vIterator = valueIt.iterator();
		boolean isK = true;
		boolean isV = true;
		while (isK || isV) {
			isK = kIterator.hasNext();
			isV = vIterator.hasNext();
			list.add(new EntryBean<K, V>(isK ? kIterator.next() : null, isV ? vIterator.next() : null));
		}
		return list;
	}

	public static <T> T getParam(Class<T> clazz, Object key, Map Context)
	{
		if (Context == null) return null;
		Object value = Context.get(key);
		if (clazz.isInstance(value))
			return (T) value;
		return null;
	}

	public static <T> T getParam(Class<T> clazz, Object key, T defaultValue, Map Context)
	{
		if (Context == null) return defaultValue;
		Object value = Context.get(key);
		if (clazz.isInstance(value))
			return (T) value;
		return defaultValue;
	}

}
