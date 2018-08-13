package sf.tquery.interfaces;


import sf.tquery.interfaces.exec.IComparator;

import java.util.Map;

public interface IEntryIterator
{
	public <K,V> Map<K,V> toMap();
	public <K,V> Map<K,V> toMap(IComparator<Map.Entry<K, V>> entryComparator) throws Exception;
}
