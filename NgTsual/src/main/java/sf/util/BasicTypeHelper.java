package sf.util;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicTypeHelper
{
	static {
		Map<Class, Class> temp = new HashMap<>();
		temp.put(long.class, Long.class);
		temp.put(Long.class, long.class);
		temp.put(long[].class, Long[].class);
		temp.put(Long[].class, long[].class);

		temp.put(Integer.class, int.class);
		temp.put(int.class, Integer.class);
		temp.put(Integer[].class, int[].class);
		temp.put(int[].class, Integer[].class);

		temp.put(Double.class, double.class);
		temp.put(double.class, Double.class);
		temp.put(Double[].class, double[].class);
		temp.put(double[].class, Double[].class);

		temp.put(Byte.class, byte.class);
		temp.put(byte.class, Byte.class);
		temp.put(Byte[].class, byte[].class);
		temp.put(byte[].class, Byte[].class);

		temp.put(Float.class, float.class);
		temp.put(float.class, Float.class);
		temp.put(Float[].class, float[].class);
		temp.put(float[].class, Float[].class);

		temp.put(Short.class, short.class);
		temp.put(short.class, Short.class);
		temp.put(Short[].class, short[].class);
		temp.put(short[].class, Short[].class);

		basicTypeEqualMap = Collections.unmodifiableMap(temp);

		temp = new HashMap<>();
		temp.put(long.class, Long.class);
		temp.put(Long.class, long.class);

		temp.put(Integer.class, int.class);
		temp.put(int.class, Integer.class);

		temp.put(Double.class, double.class);
		temp.put(double.class, Double.class);

		temp.put(Byte.class, byte.class);
		temp.put(byte.class, Byte.class);

		temp.put(Float.class, float.class);
		temp.put(float.class, Float.class);

		temp.put(Short.class, short.class);
		temp.put(short.class, Short.class);

		Map<Class, Class> uac = new HashMap<>();
		uac.put(int[].class, int.class);
		uac.put(Integer[].class, Integer.class);
		uac.put(Long[].class, Long.class);
		uac.put(long[].class, long.class);
		uac.put(Float[].class, Float.class);
		uac.put(float[].class, float.class);
		uac.put(Short[].class, Short.class);
		uac.put(short[].class, short.class);
		uac.put(Byte[].class, Byte.class);
		uac.put(byte[].class, byte.class);
		uac.put(Double[].class, Double.class);
		uac.put(double[].class, double.class);
		numberArrayClasses = Collections.unmodifiableMap(uac);
	}

	private static final Map<Class, Class> basicTypeEqualMap;
	private static final Map<Class, Class> numberArrayClasses;

	public static boolean typeEqual(Class<?> clazzA, Class<?> clazzB)
	{
		return clazzA.equals(clazzB) || clazzB.equals(basicTypeEqualMap.get(clazzA));
	}

	public static boolean typeEqualNumber(Class<?> clazzA, Class<?> clazzB)
	{
		return clazzA.equals(clazzB) || clazzB.equals(basicTypeEqualMap.get(clazzA));
	}

	public static Class getEquivalenceType(Class clazz)
	{
		return basicTypeEqualMap.get(clazz);
	}

	public static boolean typeEqualNumberArray(Class a)
	{
		return numberArrayClasses.containsKey(a);
	}

	public static Class getNumberArraySubClass(Class a){
		return numberArrayClasses.get(a);
	}

	public static <T> T transType(Class<T> clazz, Object ret)
	{
		try {
			if (ret == null || clazz.equals(ret.getClass())) {
				return (T) ret;
			} else if (clazz.equals(String.class)) {
				return (T) String.valueOf(ret);
			} else if (ret instanceof String || ret instanceof Number) {
				if (Number.class.isAssignableFrom(clazz) ||
						(int.class.equals(clazz) || clazz.equals(long.class) || clazz.equals(byte.class)
								|| clazz.equals(double.class) || clazz.equals(short.class) || clazz.equals(float.class)))
				{
					boolean isstr = ret instanceof String;
					if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
						return (T) (isstr ? Integer.valueOf((String) ret) : (Number) ((Number) ret).intValue());
					} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
						return (T) (isstr ? Long.valueOf((String) ret) : (Number) ((Number) ret).longValue());
					} else if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
						return (T) (isstr ? Byte.valueOf((String) ret) : (Number) ((Number) ret).byteValue());
					} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
						return (T) (isstr ? Double.valueOf((String) ret) : (Number) ((Number) ret).doubleValue());
					} else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
						return (T) (isstr ? Short.valueOf((String) ret) : (Number) ((Number) ret).shortValue());
					} else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
						return (T) (isstr ? Float.valueOf((String) ret) : (Number) ((Number) ret).floatValue());
					}
				} else if (Timestamp.class.isAssignableFrom(clazz)) {
					return (T) Timestamp.valueOf((String) ret);
				}
			} else if (clazz.isArray()) {
				if (ret.getClass().isArray()) {
					if (typeEqual(ret.getClass(), clazz)) {
						final int length = Array.getLength(ret);
						if (length > 0) {
							final Class tarClazz = getEquivalenceType(Array.get(ret, 0).getClass());
							final Object res = Array.newInstance(tarClazz, length);
							for (int i = 0; i < length; i++) {
								Array.set(res, i, Array.get(ret, i));
							}
							return (T) res;
						} else {
							return null;
						}
					} else if (typeEqualNumberArray(ret.getClass()) && typeEqualNumberArray(clazz)) {
						final int length = Array.getLength(ret);
						if (length > 0) {
							final Class tarClazz = getNumberArraySubClass(clazz);
							final Object res = Array.newInstance(getNumberArraySubClass(clazz), length);
							for (int i = 0; i < length; i++) {
								Array.set(res, i, transType(tarClazz, Array.get(ret, i)));
							}
							return (T) res;
						} else {
							return null;
						}
					}
				} else {
					if (typeEqualNumberArray(clazz) && ret instanceof List) {
						final int size = ((List) ret).size();
						if (size < 1) {
							return null;
						} else {
							final Class tarClazz = getNumberArraySubClass(clazz);
							final Object res = Array.newInstance(getNumberArraySubClass(clazz), size);
							for (int i = 0; i < size; i++) {
								Array.set(res, i, transType(tarClazz, ((List) ret).get(i)));
							}
							return (T) res;
						}
					}
				}
			}
			return null;
		} catch (Exception ex) {
			return null;
		}
	}

	@Deprecated
	public static class Base16Convertor
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
}
