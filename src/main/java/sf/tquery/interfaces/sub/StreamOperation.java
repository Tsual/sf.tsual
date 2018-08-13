/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery.interfaces.sub;

import sf.tquery.interfaces.preview.Iterator;
import sf.tquery.interfaces.exec.IRunnable;
import sf.tquery.interfaces.exec.ISelector;
import sf.tquery.interfaces.exec.ITypeConverter;

public interface StreamOperation<T>
{
	public <V> Iterator<V> as(ITypeConverter<T, V> tvTypeConverter) throws Exception;
	public Iterator<T> where(ISelector<T> tSelector) throws Exception;
	public T first(ISelector<T> tSelector) throws Exception;
	public Iterator<T> first(ISelector<T> tSelector, IRunnable<T> del) throws Exception;
}
