package sf.uds.util.type.convert;

import java.util.*;

public class HexConvertor
{
	private static final Map map;

	static {
		Map m = new HashMap();
		final char[] chars = "0123456789ABCDEF".toCharArray();
		for (int i = 0; i < 16; i++) {
			m.put(((Number) i).byteValue(), chars[i]);
			m.put(chars[i], ((Number) i).byteValue());
		}
		map = Collections.unmodifiableMap(m);
	}

	public static String encode(byte[] arr)
	{
		StringBuilder sb = new StringBuilder();
		for (byte b : arr)
			sb.append(map.get(b));
		return sb.toString();
	}

	public static byte[] decode(String str)
	{
		byte[] arr = new byte[str.length()];
		for (int i = 0; i < str.length(); i++)
			arr[i] = (Byte) map.get(str.charAt(i));
		return arr;
	}
}
