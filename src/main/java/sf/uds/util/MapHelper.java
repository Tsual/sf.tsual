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
}
