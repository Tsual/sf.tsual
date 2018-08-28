/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery.interfaces.preview.sub;

import sf.tquery.interfaces.preview.Iterator ;
import sf.tquery.interfaces.exec.IAction;
import sf.tquery.interfaces.exec.IRunnable;

import java.util.List;

public interface FluentOperation<T>
{
	public T first() throws Exception;
	public <V> V execute(IAction<V> action) throws Exception;
	public Iterator<T> executeWithList(IRunnable<List<T>> del) throws Exception;
	public Iterator<T> executeWithIterator(IRunnable<Iterator<T>> del) throws Exception;
}
