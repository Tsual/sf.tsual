package sf.uds.util.type;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TypeHelper
{
	static {
		Map<Class, Class> temp = new HashMap<>();
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

		basicTypeEqualMap = Collections.unmodifiableMap(temp);
	}

	private static Map<Class, Class> basicTypeEqualMap;

	public static boolean typeEqual(Class<?> clazzA, Class<?> clazzB)
	{
		return clazzA.equals(clazzB) ||
				(basicTypeEqualMap.containsKey(clazzA) && basicTypeEqualMap.get(clazzA).equals(clazzB));
	}

	public static <T> T transType(Class<T> clazz, Object ret)
	{
		try {
			if (clazz.equals(String.class) && (ret instanceof String || ret instanceof Number)) {
				return (T) String.valueOf(ret);
			} else if (ret instanceof String || ret instanceof Number) {
				if (Number.class.isAssignableFrom(clazz) || (int.class.equals(clazz) || clazz.equals(long.class) || clazz.equals(byte.class) || clazz.equals(double.class) || clazz.equals(short.class) || clazz.equals(float.class))) {
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
			}
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
}
