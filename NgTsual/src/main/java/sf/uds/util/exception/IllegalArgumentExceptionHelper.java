package sf.uds.util.exception;

import com.sun.deploy.util.StringUtils;
import com.sun.istack.internal.NotNull;
import sf.tquery.interfaces.exec.IRunnable;
import sf.tquery.interfaces.exec.ISelector;
import sf.uds.util.ObjectHelper;
import sf.uds.util.StringHelper;

import java.lang.reflect.Method;

public class IllegalArgumentExceptionHelper
{
	public static String getFullTraceInfoEn(@NotNull IllegalArgumentException ex, @NotNull Method method, Object[] args)
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
