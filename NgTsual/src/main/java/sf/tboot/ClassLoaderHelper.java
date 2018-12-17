/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tboot;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.Vector;

class ClassLoaderHelper {
    private static final Method findLoadedClass;
    private static final Method getClassLoader;
    private static final Method defineClass;
    private static final Method defineClassNio;
    private static final Method findBootstrapClass;
    private static final Method resolveClass;
    private static final Method findClass;
    private static final Method loadClass;

    static {
        findLoadedClass = find0("findLoadedClass", String.class);
        getClassLoader = find0("getClassLoader", Class.class);
        defineClass = find0("defineClass", String.class, byte[].class, int.class, int.class);
        defineClassNio = find0("defineClass", String.class, java.nio.ByteBuffer.class, ProtectionDomain.class);
        findBootstrapClass = find0("findBootstrapClass", String.class);
        resolveClass = find0("resolveClass", Class.class);
        findClass = find0("findClass", String.class);
        loadClass = find0("loadClass", String.class, boolean.class);
    }

    private static final Method find0(final String name, final Class... klasses) {
        return find0(ClassLoader.class, name, klasses);
    }

    private static final Method find0(final Class klass, final String name, final Class... klasses) {
        return AccessController.doPrivileged(new PrivilegedAction<Method>() {
            @Override
            public Method run() {
                try {
                    final Method method = klass.getDeclaredMethod(name, klasses);
                    method.setAccessible(true);
                    return method;
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    static final Class findBootstrapClass(ClassLoader loader, String name) {
        try {
            return (Class) findBootstrapClass.invoke(loader, name);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static final Class findLoadedClass(ClassLoader loader, String name) {
        try {
            return (Class) findLoadedClass.invoke(loader, name);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static final ClassLoader getClassLoader(Class<?> caller) {
        try {
            return (ClassLoader) getClassLoader.invoke(null, caller);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static final Class defineClass(ClassLoader loader, String name, byte[] b, int off, int len) {
        try {
            return (Class) defineClass.invoke(loader, name, b, off, len);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static final Class defineClass(ClassLoader loader, String name, java.nio.ByteBuffer buffer) {
        try {
            return (Class) defineClassNio.invoke(loader, name, buffer, null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static final void resolveClass(ClassLoader loader, Class<?> klass) {
        try {
            resolveClass.invoke(loader, klass);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static final Class findClass(ClassLoader loader, String str) {
        try {
            return (Class) findClass.invoke(loader, str);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static final Class loadClass(ClassLoader loader, String str, boolean b) {
        try {
            return (Class) loadClass.invoke(loader, str, b);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static final Vector<Class<?>> getFieldClasses(final ClassLoader loader) {
        return AccessController.doPrivileged(new PrivilegedAction<Vector<Class<?>>>() {
            @Override
            public Vector<Class<?>> run() {
                try {
                    final Field klasses = ClassLoader.class.getDeclaredField("classes");
                    klasses.setAccessible(true);
                    return (Vector<Class<?>>) klasses.get(loader);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
