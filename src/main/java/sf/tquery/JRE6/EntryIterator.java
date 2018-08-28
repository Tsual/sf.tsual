/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery.JRE6;

import sf.tquery.interfaces.old.IEntryIterator;
import sf.tquery.interfaces.exec.IComparator;
import sf.uds.interfaces.ctx.IBaseContext;

import java.util.HashMap;
import java.util.Map;

class EntryIterator<T extends Map.Entry> extends DefaultIterator<T> implements IEntryIterator
{
	EntryIterator(Iterable<T> it)
	{
		super(it);
	}

	EntryIterator(T[] ar)
	{
		super(ar);
	}

	EntryIterator(Iterable<T> it, Map ctx)
	{
		super(it, ctx);
	}

	EntryIterator(T[] ar, Map ctx)
	{
		super(ar, ctx);
	}

	public <K, V> Map<K, V> toMap()
	{
		HashMap<K, V> map = new HashMap<K, V>();
		for (Map.Entry<K, V> t : toIterable()) {
			map.put(t.getKey(), t.getValue());
		}
		return map;
	}

	@Override
	public <K, V> Map<K, V> toMap(IComparator<Map.Entry<K, V>> entryComparator) throws Exception
	{
		if (entryComparator instanceof IBaseContext)
			this.vauExecutable((IBaseContext) entryComparator);
		HashMap<K, V> map = new HashMap<K, V>();
		for (Map.Entry<K, V> t : toIterable()) {
			if (map.containsKey(t.getKey())) {
				Map.Entry<K, V> var = entryComparator.execute((Map.Entry<K, V>) map.get(t.getKey()), t);
				map.put(var.getKey(), var.getValue());
			} else {
				map.put(t.getKey(), t.getValue());
			}
		}
		return map;
	}

}
