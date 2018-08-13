package sf.util;

import sf.tquery.JRE6.Iterators;
import sf.tquery.interfaces.exec.IAction;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.*;
import java.util.jar.JarEntry;

public final class FrameHelper
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

		basicTypeEqualMap = temp;
	}

	private static Map<Class, Class> basicTypeEqualMap;

	public static boolean basicTypeEqual(Class<?> clazzA, Class<?> clazzB)
	{
		return basicTypeEqualMap.containsKey(clazzA) && basicTypeEqualMap.get(clazzA).equals(clazzB);
	}

	private static Class basictypetrans(String str) throws ClassNotFoundException
	{
		if ("Long".equals(str)) {
			return Long.class;
		} else if ("Integer".equals(str)) {
			return Integer.class;
		} else if ("String".equals(str)) {
			return String.class;
		} else if ("long".equals(str)) {
			return long.class;
		} else if ("int".equals(str)) {
			return int.class;
		} else if ("Long[]".equals(str)) {
			return Long[].class;
		} else if ("long[]".equals(str)) {
			return long[].class;
		} else if ("int[]".equals(str)) {
			return int[].class;
		} else if ("Integer[]".equals(str)) {
			return Integer[].class;
		}
		return Class.forName(str);
	}

	public static Class[] genClazz(String spclassString)
	{
		String[] strs = spclassString.split(",");
		List<Class> clzs = new ArrayList<Class>();
		try {
			for (String str : strs)
				clzs.add((basictypetrans(str)));
		} catch (Exception ex) {
			return new Class[0];
		}
		return clzs.toArray(new Class[0]);
	}

	public static String strJoin(Object[] objs, String jspt)
	{
		StringBuilder res = new StringBuilder(objs.length > 0 ? objs[0].toString() : "");
		for (int i = 1; i < objs.length; i++) {
			res.append(jspt).append(objs[i].toString());
		}
		return res.toString();
	}

	public static <T> T transBasicType(Class<T> clazz, Object ret)
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

	public static Object appendObject(Object o1, Object o2)
	{
		Class<?> clazz;
		if ((clazz = o1.getClass()).equals(o2.getClass())) {
			if (clazz.isArray()) {
				int len1 = Array.getLength(o1);
				int len2 = Array.getLength(o2);
				if (len1 + len2 > 0) {
					Object templateObject = len1 == 0 ? Array.get(o2, 0) : Array.get(o1, 0);
					Object o3 = Array.newInstance(templateObject.getClass(), len1 + len2);
					for (int i = 0; i < len1 + len2; i++)
						Array.set(o3, i, i < len1 ? Array.get(o1, i) : Array.get(o2, i - len1));
					o1 = o3;
				}
			} else if (o1 instanceof Collection) {
				o1 = ((Collection) o1).addAll((Collection) o2);
			}
		}
		return o1;
	}

	public static Collection<String> splitStringToSubstring(String str, String splStr)
	{
		return splitStringToSubstring(str, splStr, new ArrayList<String>());
	}

	private static Collection<String> splitStringToSubstring(String str, String splStr, Collection<String> col)
	{
		if (str.contains(splStr)) {
			String sub = str.substring(str.indexOf(splStr) + splStr.length());
			col.add(sub);
			return splitStringToSubstring(sub, splStr, col);
		} else {
			return col;
		}
	}

	public static List splitTableNamesFromSQL(String sql) throws Exception
	{
		final ArrayList<String> res = new ArrayList<>();
		return Iterators.getIterator(FrameHelper.splitStringToSubstring(sql.toLowerCase(), " from "))
				.context(obj -> obj.put(1, new ArrayList<String>()))
				.as(obj -> obj.trim())
				.where(obj -> !obj.startsWith("("))
				.as(obj -> obj.contains(" where ") ? obj.substring(0, obj.indexOf(" where ")) : obj)
				.foreach(obj -> Iterators.getIterator(obj.split(","))
						.foreach(obj1 ->
						{
							obj1 = obj1.trim();
							res.add(obj1.contains(" ") ? obj1.substring(0, obj1.indexOf(" ")) : obj1);
						}))
				.execute((IAction<List>) () -> res);
	}

	public static <K, V> Map<K, V> genHashMap(Map.Entry<K, V>... entries)
	{
		Map<K, V> map = new HashMap<K, V>();
		for (Map.Entry<K, V> entry : entries)
			map.put(entry.getKey(), entry.getValue());
		return map;
	}

	public static List<String> getResourceInPackage(final Boolean _IsRecursive, String _Package) throws IOException
	{
		String _Package_ls = _Package.replace(".", "/");
		Enumeration<URL> urls = ClassLoader.getSystemClassLoader().getResources(_Package_ls);
		List<String> _ClassNames = new ArrayList<>();
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			if ("file".equals(url.getProtocol()))
				for (File classFile : Objects.requireNonNull((new File(URLDecoder.decode(url.getFile(), "UTF-8"))).listFiles(pathname -> (_IsRecursive & pathname.isDirectory()) | pathname.getName().endsWith(".class")))) {
					if (classFile.isDirectory())
						_ClassNames.addAll(getResourceInPackage(_IsRecursive, _Package + "." + classFile.getName()));
					else if (classFile.isFile())
						_ClassNames.add(_Package + "." + classFile.getName().substring(0, classFile.getName().length() - 6));
				}
			else if ("jar".equals(url.getProtocol())) {
				Enumeration<JarEntry> jares = ((JarURLConnection) url.openConnection()).getJarFile().entries();
				while (jares.hasMoreElements()) {
					String jare_name = jares.nextElement().getName();
					if (jare_name.endsWith(".class"))
						if (_IsRecursive)
							_ClassNames.add(jare_name.replace("/", ".").substring(0, jare_name.length() - 6));
						else if (jare_name.startsWith(_Package_ls) && jare_name.substring(_Package_ls.length() + 1).split("/").length == 1)
							_ClassNames.add(jare_name.replace("/", ".").substring(0, jare_name.length() - 6));
				}
			}
		}
		return _ClassNames;
	}
}
