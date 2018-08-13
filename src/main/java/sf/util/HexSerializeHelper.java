package sf.util;

import java.io.*;
import java.util.Map;

public final class HexSerializeHelper
{
	public static String SerializeMapToHex(Map m)
	{
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			SerializeObject(m, os);
			return bytesToHexString(os.toByteArray());
		} catch (Exception ex) {
			return "";
		}
	}

	public static void SerializeObject(Object obj, OutputStream os) throws IOException
	{
		new ObjectOutputStream(os).writeObject(obj);
	}

	public static Map DeserializeMapFromHex(String str)
	{
		return DeserializeFromHex(str);
	}

	public static <T> T DeserializeFromHex(String str)
	{
		try {
			byte[] tt = hexStringToBytes(str);
			ByteArrayInputStream is = new ByteArrayInputStream(tt);
			return Deserialize(is);
		} catch (Exception ex) {
			return null;
		}
	}

	public static <T> T Deserialize(InputStream s)
	{
		try {
			return (T) new ObjectInputStream(s).readObject();
		} catch (Exception ex) {
			return null;
		}
	}

	private static String bytesToHexString(byte[] src)
	{
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (byte aSrc : src) {
			int v = aSrc & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	private static byte[] hexStringToBytes(String hexString)
	{
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static final String strMap = "0123456789ABCDEF";
	private static byte charToByte(char c)
	{
		return (byte) strMap.indexOf(c);
	}
}
