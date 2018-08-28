package sf.uds.util.type.convert;

import sf.uds.util.type.BasicTypeComparator;

import java.lang.reflect.Array;
import java.sql.Timestamp;

public class BasicConvertor
{
	public static <T> T transType(Class<T> clazz, Object ret)
	{
		try {
			if (clazz.equals(String.class)) {
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
			} else if (ret.getClass().isArray() && clazz.isArray()
					&& BasicTypeComparator.typeEqual(ret.getClass(), clazz))
			{
				final int length = Array.getLength(ret);
				if (length > 0) {
					final Class tarClazz = BasicTypeComparator.getEquivalenceType(Array.get(ret, 0).getClass());
					final Object res = Array.newInstance(tarClazz, length);
					for (int i = 0; i < length; i++) {
						Array.set(res, i, Array.get(ret, i));
					}
					return (T) res;
				} else {
					return null;
				}
			}
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
}
