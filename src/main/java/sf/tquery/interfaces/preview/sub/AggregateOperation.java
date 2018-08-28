/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery.interfaces.preview.sub;

import sf.tquery.interfaces.preview.Iterator;
import sf.tquery.interfaces.exec.ISelector;
import sf.tquery.interfaces.exec.ITypeConverter;
import sf.uds.interfaces.del.executable.IExec_1;

public interface AggregateOperation<T>
{
	public Iterator<T> insert(ISelector<T> locationSelector, IExec_1<Iterator<T>, T> insertCollectionGetter) throws Exception;
	public <V> Iterator<V> convert(ITypeConverter<T, Iterable<V>> tvTypeConverter) throws Exception;
}
