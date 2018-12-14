/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tboot;

import sun.misc.Resource;
import sun.misc.URLClassPath;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.security.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class TBootClassLoader extends URLClassLoader
{
	private Map<String, Class> replaceClasses = new HashMap<String, Class>();
	private List<ClassLoader> slaves = new ArrayList<ClassLoader>();

	/* The search path for classes and resources */
	private final URLClassPath ucp0;

	/* The context to be used when loading classes and resources */
	private final AccessControlContext acc0;

	public static TBootClassLoader newInstance()
	{
		final ClassLoader classLoader = ClassLoaderHelper.getClassLoader(TBootClassLoader.class);
		if (classLoader instanceof URLClassLoader) {
			return new TBootClassLoader(((URLClassLoader) classLoader).getURLs(), classLoader);
		} else {
			return new TBootClassLoader(new URL[0], classLoader);
		}
	}


	private TBootClassLoader(URL[] urls, ClassLoader parent)
	{
		super(urls, parent);
		this.acc0 = AccessController.getContext();
		ucp0 = new URLClassPath(urls, acc0);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		return replaceClasses.containsKey(name) ? replaceClasses.get(name) : super.findClass(name);
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException
	{
		final Class aClass = bootClass(name);
		return aClass == null ? super.loadClass(name) : aClass;
	}

	public Class bootClass(final String name) throws ClassNotFoundException
	{
		final Class<?> result;
		try {
			result = AccessController.doPrivileged(
					new PrivilegedExceptionAction<Class<?>>()
					{
						public Class<?> run() throws ClassNotFoundException
						{
							String path = name.replace('.', '/').concat(".class");
							Resource res = ucp0.getResource(path, false);
							if (res != null) {
								try {
									return defineClass(name, res);
								} catch (IOException e) {
									throw new ClassNotFoundException(name, e);
								}
							} else {
								return null;
							}
						}
					}, acc0);
		} catch (PrivilegedActionException pae) {
			throw (ClassNotFoundException) pae.getException();
		}
		if (result == null) {
			throw new ClassNotFoundException(name);
		}
		return result;
	}

	private Class<?> defineClass(String name, Resource res) throws IOException
	{
		long t0 = System.nanoTime();
		int i = name.lastIndexOf('.');
		URL url = res.getCodeSourceURL();
		if (i != -1) {
			String pkgname = name.substring(0, i);
			// Check if package already loaded.
			Manifest man = res.getManifest();
			definePackageInternal(pkgname, man, url);
		}
		// Now read the class bytes and define the class
		ByteBuffer bb = res.getByteBuffer();
		if (bb != null) {
			// Use (direct) ByteBuffer:
			CodeSigner[] signers = res.getCodeSigners();
			CodeSource cs = new CodeSource(url, signers);
			sun.misc.PerfCounter.getReadClassBytesTime().addElapsedTimeFrom(t0);
			return defineClass(name, bb, cs);
		} else {
			byte[] b = res.getBytes();
			// must read certificates AFTER reading bytes.
			CodeSigner[] signers = res.getCodeSigners();
			CodeSource cs = new CodeSource(url, signers);
			sun.misc.PerfCounter.getReadClassBytesTime().addElapsedTimeFrom(t0);
			return defineClass(name, b, 0, b.length, cs);
		}
	}

	private void definePackageInternal(String pkgname, Manifest man, URL url)
	{
		if (getAndVerifyPackage(pkgname, man, url) == null) {
			try {
				if (man != null) {
					definePackage(pkgname, man, url);
				} else {
					definePackage(pkgname, null, null, null, null, null, null, null);
				}
			} catch (IllegalArgumentException iae) {
				// parallel-capable class loaders: re-verify in case of a
				// race condition
				if (getAndVerifyPackage(pkgname, man, url) == null) {
					// Should never happen
					throw new AssertionError("Cannot find package " +
							pkgname);
				}
			}
		}
	}

	private Package getAndVerifyPackage(String pkgname,
	                                    Manifest man, URL url)
	{
		Package pkg = getPackage(pkgname);
		if (pkg != null) {
			// Package found, so check package sealing.
			if (pkg.isSealed()) {
				// Verify that code source URL is the same.
				if (!pkg.isSealed(url)) {
					throw new SecurityException(
							"sealing violation: package " + pkgname + " is sealed");
				}
			} else {
				// Make sure we are not attempting to seal the package
				// at this code source URL.
				if ((man != null) && isSealed(pkgname, man)) {
					throw new SecurityException(
							"sealing violation: can't seal package " + pkgname +
									": already loaded");
				}
			}
		}
		return pkg;
	}

	private boolean isSealed(String name, Manifest man)
	{
		String path = name.replace('.', '/').concat("/");
		Attributes attr = man.getAttributes(path);
		String sealed = null;
		if (attr != null) {
			sealed = attr.getValue(Attributes.Name.SEALED);
		}
		if (sealed == null) {
			if ((attr = man.getMainAttributes()) != null) {
				sealed = attr.getValue(Attributes.Name.SEALED);
			}
		}
		return "true".equalsIgnoreCase(sealed);
	}

	public Class bootClass(String name, byte[] array)
	{
		final Class<?> loadedClass = findLoadedClass(name);
		if (loadedClass == null) {
			return defineClass(name, array, 0, array.length);
		} else {
			final Class aClass = ClassLoaderHelper.defineClass(catchOneSlave(name), name, array, 0, array.length);
			replaceClasses.put(name, aClass);
			return aClass;
		}
	}

	public Class bootClass(String name, ByteBuffer buffer)
	{
		final Class<?> loadedClass = findLoadedClass(name);
		if (loadedClass == null) {
			return defineClass(name, buffer, (ProtectionDomain) null);
		} else {
			final Class aClass = ClassLoaderHelper.defineClass(catchOneSlave(name), name, buffer);
			replaceClasses.put(name, aClass);
			return aClass;
		}
	}

	private ClassLoader catchOneSlave(String name)
	{
		final ClassLoader selfLoader = ClassLoaderHelper.getClassLoader(TBootClassLoader.class);
		final Class loadedClass = ClassLoaderHelper.findLoadedClass(selfLoader, name);
		ClassLoader slave = null;
		for (ClassLoader cl : slaves)
			if (!ClassLoaderHelper.getFieldClasses(cl).contains(loadedClass)) {
				slave = cl;
				break;
			}
		if (slave == null) {
			slave = new ClassLoader()
			{
			};
			slaves.add(slave);
		}
		return slave;
	}
}
