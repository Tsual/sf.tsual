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
import sun.reflect.Reflection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class TBootClassLoader extends ClassLoader
{
	private final List<ClassLoader> def_slaves = new ArrayList<>();
	private final Map<String, Class> tup_map = new HashMap<>();
	private final URLClassLoader slave_loader;
	private final URLClassPath ucp;
	private final AccessControlContext acc;

	public TBootClassLoader()
	{
		super(null);
		slave_loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		acc = AccessController.getContext();
		ucp = new URLClassPath(slave_loader.getURLs(), acc);
	}


	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		if (tup_map.containsKey(name))
			return tup_map.get(name);
		else {
			Resource res = ucp.getResource(name.replace('.', '/').concat(".class"), false);
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
	}

	private Class boot0(String name, Class clazz)
	{
		tup_map.put(name, clazz);
		return clazz;
	}

	Class bootClass(String name, byte[] buffer)
	{
		return boot0(name, ClassLoaderHelper.defineClass(catch_undefine_slave(name), name, buffer, 0, buffer.length));
	}

	Class bootClass(String name, ByteBuffer buffer)
	{
		return boot0(name, ClassLoaderHelper.defineClass(catch_undefine_slave(name), name, buffer));
	}

	private ClassLoader catch_undefine_slave(String name)
	{
		for (ClassLoader clt : def_slaves) {
			boolean fr = true;
			for (Class clazz_t : ClassLoaderHelper.getFieldClasses(clt)) {
				if (clazz_t.getName().equals(name)) {
					fr = false;
					break;
				}
			}
			if (fr) return clt;
		}

		return new TBootURLClassLoader(slave_loader.getURLs(), this);
	}


	private Class<?> defineClass(String name, Resource res) throws IOException
	{
		int i = name.lastIndexOf('.');
		URL url = res.getCodeSourceURL();
		if (i != -1) {
			String pkgname = name.substring(0, i);
			Manifest man = res.getManifest();
			definePackageInternal(pkgname, man, url);
		}
		java.nio.ByteBuffer bb = res.getByteBuffer();
		if (bb != null) {
			return defineClass(name, bb, null);
		} else {
			byte[] b = res.getBytes();
			return defineClass(name, b, 0, b.length);
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

	protected Package definePackage(String name, Manifest man, URL url)
			throws IllegalArgumentException
	{
		String path = name.replace('.', '/').concat("/");
		String specTitle = null, specVersion = null, specVendor = null;
		String implTitle = null, implVersion = null, implVendor = null;
		String sealed = null;
		URL sealBase = null;

		Attributes attr = man.getAttributes(path);
		if (attr != null) {
			specTitle = attr.getValue(Attributes.Name.SPECIFICATION_TITLE);
			specVersion = attr.getValue(Attributes.Name.SPECIFICATION_VERSION);
			specVendor = attr.getValue(Attributes.Name.SPECIFICATION_VENDOR);
			implTitle = attr.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
			implVersion = attr.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
			implVendor = attr.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
			sealed = attr.getValue(Attributes.Name.SEALED);
		}
		attr = man.getMainAttributes();
		if (attr != null) {
			if (specTitle == null) {
				specTitle = attr.getValue(Attributes.Name.SPECIFICATION_TITLE);
			}
			if (specVersion == null) {
				specVersion = attr.getValue(Attributes.Name.SPECIFICATION_VERSION);
			}
			if (specVendor == null) {
				specVendor = attr.getValue(Attributes.Name.SPECIFICATION_VENDOR);
			}
			if (implTitle == null) {
				implTitle = attr.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
			}
			if (implVersion == null) {
				implVersion = attr.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
			}
			if (implVendor == null) {
				implVendor = attr.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
			}
			if (sealed == null) {
				sealed = attr.getValue(Attributes.Name.SEALED);
			}
		}
		if ("true".equalsIgnoreCase(sealed)) {
			sealBase = url;
		}
		return definePackage(name, specTitle, specVersion, specVendor,
				implTitle, implVersion, implVendor, sealBase);
	}
}
