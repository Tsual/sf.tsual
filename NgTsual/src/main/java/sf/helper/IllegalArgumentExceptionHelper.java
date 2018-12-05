package sf.helper;

import sf.uds.util.StringHelper;

import java.lang.reflect.Method;

public class IllegalArgumentExceptionHelper
{
	public static String getFullTraceInfoEn(IllegalArgumentException ex, Method method, Object[] args)
	{
		StringBuilder sb = new StringBuilder(ex.getMessage()).append("\nReflect information\n");
		for (Class method_arg_class : method.getParameterTypes())
			sb.append("  Type:")
					.append(method_arg_class.toString())
					.append("||Interface:")
					.append(StringHelper.join(method_arg_class.getInterfaces(), ","))
					.append("\n");
		sb.append("Argument information\n");
		for (Object arg : args)
			sb.append("  String value:").append(arg.toString())
					.append("||Type:").append(arg.getClass().toString())
					.append("||Interface:")
					.append(StringHelper.join(arg.getClass().getInterfaces(), ","))
					.append("\n");
		return sb.toString();
	}
}
