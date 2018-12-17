/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tboot;

import sun.reflect.Reflection;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TBootstrapper {
    public static Class<?> load(String fullClassName, File javaClassFile) {
        final int BUFFER_SIZE = 1024;
        final ClassLoader loader = preBoot(fullClassName);
        try {
            if (javaClassFile != null && javaClassFile.isFile()) {
                final ArrayList<byte[]> list = new ArrayList<byte[]>();
                FileInputStream fis = new FileInputStream(javaClassFile);
                try {
                    int read_count;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while (BUFFER_SIZE == (read_count = fis.read(buffer))) {
                        list.add(buffer);
                        buffer = new byte[BUFFER_SIZE];
                    }

                    byte[] data = new byte[BUFFER_SIZE * list.size() + read_count];
                    for (int i = 0; i < list.size(); i++)
                        System.arraycopy(list.get(i), 0, data, BUFFER_SIZE * i, BUFFER_SIZE);
                    if (read_count >= 0) System.arraycopy(buffer, 0, data, BUFFER_SIZE * list.size(), read_count);
                    return load0(fullClassName, data, loader);
                } finally {
                    fis.close();
                }
            } else {
                return null;
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static Class<?> loadCode0(String fullClassName, final String javaCode, List<String> ext_classes, ClassLoader loader) {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final JavaFileManager file_manager = new ForwardingJavaFileManager(compiler.getStandardFileManager(null, null, null)) {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
                final URI class_uri = URI.create("string:///" + className.replace('.', '/') + kind.extension);
                return new SimpleJavaFileObject(class_uri, kind) {
                    @Override
                    public OutputStream openOutputStream() {
                        return bos;
                    }
                };
            }
        };

        DiagnosticListener<JavaFileObject> dl = new DiagnosticCollector<JavaFileObject>();
        final List<String> compile_param = Arrays.asList("-encoding", "UTF-8", "-classpath", System.getProperty("java.class.path"), "-Xlint:unchecked");

        final URI source_uri = URI.create("string:///" + fullClassName.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension);
        final JavaFileObject source = new SimpleJavaFileObject(source_uri, JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return javaCode;
            }
        };

        try {
            if (compiler.getTask(null, file_manager, dl, compile_param, ext_classes, Collections.singletonList(source)).call()) {
                return load0(fullClassName, bos.toByteArray(), loader);
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            if (cause instanceof InvocationTargetException) cause = cause.getCause();
            if (cause instanceof NoClassDefFoundError) {
                final String message = cause.getMessage();
                final String subClass = message.substring(message.indexOf("(wrong name: ") + 13, message.length() - 1).replace("/", ".");
                if (subClass.indexOf(fullClassName) == 0) {
                    loadCode0(subClass, javaCode, ext_classes, loader);
                    ext_classes.add(subClass);
                    return loadCode0(fullClassName, javaCode, ext_classes, loader);
                }
            }
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?> load(String fullClassName, String javaCode) {
        return loadCode0(fullClassName, javaCode, new ArrayList<String>(), preBoot(fullClassName));
    }

    public static Class<?> load(String fullClassName, byte[] javaClassByteArray) {
        return load0(fullClassName, javaClassByteArray, preBoot(fullClassName));
    }

    public static Class<?> load(String fullClassName, java.nio.ByteBuffer javaClassNioBuffer) {
        return load0(fullClassName, javaClassNioBuffer, preBoot(fullClassName));
    }

    private static void retriveCl0(final ClassLoader callerClassLoader) {
        if (!Thread.currentThread().getContextClassLoader().equals(callerClassLoader))
            Thread.currentThread().setContextClassLoader(callerClassLoader);
    }

    private static Class<?> load0(final String name, final byte[] buffer, final ClassLoader callerClassLoader) {
        if (buffer != null) {
            retriveCl0(callerClassLoader);
            if (callerClassLoader instanceof TBootClassLoader) {
                return ((TBootClassLoader) callerClassLoader).bootClass(name, buffer);
            } else {
                return ClassLoaderHelper.defineClass(callerClassLoader, name, buffer, 0, buffer.length);
            }
        } else {
            return null;
        }
    }

    private static Class<?> load0(final String name, final java.nio.ByteBuffer buffer, final ClassLoader callerClassLoader) {
        if (buffer != null) {
            retriveCl0(callerClassLoader);
            if (callerClassLoader instanceof TBootClassLoader) {
                return ((TBootClassLoader) callerClassLoader).bootClass(name, buffer);
            } else {
                return ClassLoaderHelper.defineClass(callerClassLoader, name, buffer);
            }
        } else {
            return null;
        }
    }

    private static ClassLoader preBoot(String fullClassName) {
        final ClassLoader loader = Reflection.getCallerClass(3).getClassLoader();
        Class clazz = ClassLoaderHelper.findLoadedClass(loader, fullClassName);
        if (clazz == null || loader instanceof TBootClassLoader) {
            return loader;
        } else if (loader instanceof TBootURLClassLoader) {
            return loader.getParent();
        } else {
            return new TBootClassLoader();
        }
    }

}