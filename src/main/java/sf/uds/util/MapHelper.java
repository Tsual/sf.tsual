package sf.uds.util;

import java.util.HashMap;
import java.util.Map;

public class MapHelper
{
	public static <K, V> Map<K, V> genHashMap(Map.Entry<K, V>... entries)
	{
		Map<K, V> map = new HashMap<K, V>();
		for (Map.Entry<K, V> entry : entries)
			map.put(entry.getKey(), entry.getValue());
		return map;
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
