/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Deprecated
public class MethodInvocationBean
{
	protected Method method;
	protected Object instance;
	protected Object[] args;

	public MethodInvocationBean(Method method, Object instance, Object[] args)
	{
		this.method = method;
		this.instance = instance;
		this.args = args;
	}

	public Method getMethod()
	{
		return method;
	}

	public void setMethod(Method method)
	{
		this.method = method;
	}

	public Object getInstance()
	{
		return instance;
	}

	public void setInstance(Object instance)
	{
		this.instance = instance;
	}

	public Object[] getArgs()
	{
		return args;
	}

	public void setArgs(Object[] args)
	{
		this.args = args;
	}

	public Object invoke() throws InvocationTargetException, IllegalAccessException
	{
		return method.invoke(instance, args);
	}

	public Object invoke(Object[] args) throws InvocationTargetException, IllegalAccessException{
		Object[] o_arg=this.args;
		this.args=args;
		try{
			return invoke();
		}finally {
			this.args=o_arg;
		}
	}
}

