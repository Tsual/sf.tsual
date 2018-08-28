package sf.uds.util.type;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BasicTypeComparator
{
	static {
		Map<Class, Class> temp = new HashMap<Class, Class>();
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
	}

	private static Map<Class, Class> basicTypeEqualMap;

	public static boolean typeEqual(Class<?> clazzA, Class<?> clazzB)
	{
		return clazzA.equals(clazzB) || clazzB.equals(basicTypeEqualMap.get(clazzA));
	}

	public static Class getEquivalenceType(Class clazz){
		return basicTypeEqualMap.get(clazz);
	}

}
