/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery.JRE6;

import sf.tquery.interfaces.exec.ISelector;
import sf.uds.interfaces.ctx.IBaseContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class VirtualIterator<T> extends Iterators implements Iterable<T>
{
	Map _Context;
	Iterable<T> _InnerIt;
	List<ISelector<T>> _InnerSelectors;

	void vauExecutable(IBaseContext baseContext)
	{
		if (baseContext == null) return;
		if (_Context == null) _Context = new HashMap();
		baseContext.setContext(_Context);
	}

}
