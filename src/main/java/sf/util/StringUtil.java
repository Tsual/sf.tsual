package sf.util;

public class StringUtil
{
	public static boolean isEmpty(String str)
	{
		return str == null || "".equals(str);
	}

	public static boolean isNotEmpty(String str)
	{
		return !isEmpty(str);
	}

	public static boolean isNull(String str)
	{
		return str == null;
	}

	public static boolean inNotNull(String str)
	{
		return !isNull(str);
	}

	public static String firstCharUpper(String str)
	{
		return isEmpty(str) ? str : Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	public static String firstCharLower(String str)
	{
		return isEmpty(str) ? str : Character.toLowerCase(str.charAt(0)) + str.substring(1);
	}
}
