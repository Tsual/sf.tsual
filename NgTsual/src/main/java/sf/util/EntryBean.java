/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.util;

import java.util.Map;

public class EntryBean<K, V> implements Map.Entry<K, V>
{
	private K key;
	private V value;

	public EntryBean(K key)
	{
		this.key = key;
	}

	public EntryBean(K key, V value)
	{
		this.key = key;
		this.value=value;
	}

	@Override
	public K getKey()
	{
		return key;
	}

	@Override
	public V getValue()
	{
		return value;
	}

	@Override
	public V setValue(V value)
	{
		this.value = value;
		return this.value;
	}
}
