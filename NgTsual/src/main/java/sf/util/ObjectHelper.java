package sf.util;

import java.lang.reflect.Array;

public class ObjectHelper
{
	public static <T> T requireAllNotNull(T t)
	{
		if (isAllNotNull(t)) throw new NullPointerException();
		else return t;
	}

	public static boolean isAllNotNull(Object arr)
	{
		if (arr == null) return false;
		else if (arr.getClass().isArray()) {
			boolean res = true;
			for (int i = 0; i < Array.getLength(arr); i++)
				res &= isAllNotNull(Array.get(arr, i));
			return res;
		} else if (arr instanceof Iterable) {
			boolean res = true;
			for (Object t : (Iterable) arr)
				res &= isAllNotNull(t);
			return res;
		} else {
			return true;
		}
	}
}
