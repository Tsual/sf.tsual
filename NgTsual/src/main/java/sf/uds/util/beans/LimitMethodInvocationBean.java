/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.util.beans;

import sf.uds.interfaces.del.executable.IExec_1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Deprecated
public class LimitMethodInvocationBean extends MethodInvocationBean
{

	public LimitMethodInvocationBean(Method method, Object instance, Object[] args)
	{
		super(method, instance, args);
	}

	protected IExec_1<Boolean, Method> methodLimit;
	protected IExec_1<Boolean, Object> instanceLimit;
	protected IExec_1<Boolean, Object[]> argsLimit;

	public IExec_1<Boolean, Method> getMethodLimit()
	{
		return methodLimit;
	}

	public void setMethodLimit(IExec_1<Boolean, Method> methodLimit)
	{
		this.methodLimit = methodLimit;
	}

	public IExec_1<Boolean, Object> getInstanceLimit()
	{
		return instanceLimit;
	}

	public void setInstanceLimit(IExec_1<Boolean, Object> instanceLimit)
	{
		this.instanceLimit = instanceLimit;
	}

	public IExec_1<Boolean, Object[]> getArgsLimit()
	{
		return argsLimit;
	}

	public void setArgsLimit(IExec_1<Boolean, Object[]> argsLimit)
	{
		this.argsLimit = argsLimit;
	}

	public boolean verify()
	{
		try {
			return (methodLimit == null || methodLimit.execute(method))
					&& (instanceLimit == null || instanceLimit.execute(instance))
					&& (argsLimit == null || argsLimit.execute(args));
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Object invoke() throws InvocationTargetException, IllegalAccessException
	{
		if (verify())
			return super.invoke();
		return null;
	}
}
