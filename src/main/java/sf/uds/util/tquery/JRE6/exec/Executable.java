/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.util.tquery.JRE6.exec;

import sf.uds.interfaces.ctx.IBaseContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Executable implements IBaseContext
{
	private Map Context;
	private static final Map EmptyMap= Collections.unmodifiableMap(new HashMap());

	public Map getContext()
	{
		return Context==null?EmptyMap:this.Context;
	}

	public void setContext(Map Context)
	{
		this.Context = Context;
	}

	public <T> T getParam(Class<T> clazz,Object key){
		if(Context==null)return null;
		Object value=Context.get(key);
		if(clazz.isInstance(value))
			return (T)value;
		return null;
	}

	public <T> T getParam(Class<T> clazz,Object key,T defaultValue){
		if(Context==null)return defaultValue;
		Object value=Context.get(key);
		if(clazz.isInstance(value))
			return (T)value;
		return defaultValue;
	}

	public void clearContext(){
		setContext(null);
	}
}
