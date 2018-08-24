package sf.hibernate.service;

import org.hibernate.cfg.Configuration;
import sf.resource.JarHelper;
import sf.resource.ResourceHelper;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class HibernateServiceFactory
{
	private static final Map<Class, List<Class>> implCache;

	static {
		JarHelper.loadJar("lib");
		implCache = Collections.unmodifiableMap(ResourceHelper.genImplCache(HibernateServiceFactory.class.getPackage().getName()));
	}


	public static <T> T getService(Class<T> tClass)
	{
		try {
			final List<Class> classes = implCache.get(tClass);
			if (classes != null && classes.size() == 1) {
				final Class tar_clazz = classes.get(0);
				final Constructor constructor = tar_clazz.getConstructor();
				constructor.setAccessible(true);
				return (T) constructor.newInstance();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
