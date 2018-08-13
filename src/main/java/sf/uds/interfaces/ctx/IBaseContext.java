/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.interfaces.ctx;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface IBaseContext
{
	public Map getContext();

	public void setContext(Map Context);

	public void clearContext();

	public <T> T getParam(Class<T> clazz, Object key);

	public <T> T getParam(Class<T> clazz, Object key, T defaultValue);
}
