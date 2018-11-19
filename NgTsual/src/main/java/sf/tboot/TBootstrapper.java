package sf.tboot;

import sun.reflect.Reflection;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.Buffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TBootstrapper
{
	private final static int BUFFER_SIZE = 1024;

	public static Class<?> load(String fullClassName, File javaClassFile)
	{
		Class clazz = getLoadedClass(fullClassName);
		if (clazz != null) return clazz;

		try {
			if (javaClassFile != null && javaClassFile.isFile()) {
				final ArrayList<byte[]> list = new ArrayList<>();
				try (FileInputStream fis = new FileInputStream(javaClassFile)) {
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
					return load0(fullClassName, data);
				}
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static Class<?> load(String fullClassName, String javaCode)
	{
		Class clazz = getLoadedClass(fullClassName);
		if (clazz != null) return clazz;

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

			final JavaFileManager file_manager = new ForwardingJavaFileManager(compiler.getStandardFileManager(null, null, null))
			{
				@Override
				public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling)
				{
					final URI class_uri = URI.create("string:///" + className.replace('.', '/') + kind.extension);
					return new SimpleJavaFileObject(class_uri, kind)
					{
						@Override
						public OutputStream openOutputStream()
						{
							return bos;
						}
					};
				}
			};

			final List<String> compile_param = Arrays.asList("-encoding", "UTF-8");

			final URI source_uri = URI.create("string:///" + fullClassName.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension);
			final JavaFileObject source = new SimpleJavaFileObject(source_uri, JavaFileObject.Kind.SOURCE)
			{
				@Override
				public CharSequence getCharContent(boolean ignoreEncodingErrors)
				{
					return javaCode;
				}
			};

			if (compiler.getTask(null, file_manager, null, compile_param, null, Collections.singletonList(source)).call()) {
				return load0(fullClassName, bos.toByteArray());
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Class<?> load(String fullClassName, Buffer javaClassNioBuffer)
	{
		Class clazz = getLoadedClass(fullClassName);
		if (clazz != null) return clazz;
		if (javaClassNioBuffer.hasArray())
			return load0(fullClassName, (byte[]) javaClassNioBuffer.array());
		else return null;
	}

	private static Class getLoadedClass(String fullClassName)
	{
		return AccessController.doPrivileged((PrivilegedAction<Class>) () ->
		{
			Class clazz;
			try {
				final Method findLoadedClass = ClassLoader.class.getDeclaredMethod("findLoadedClass", String.class);
				findLoadedClass.setAccessible(true);

				clazz = (Class) findLoadedClass.invoke(ClassLoader.getSystemClassLoader(), fullClassName);
				if (clazz != null) return clazz;

				final Method getClassLoader = ClassLoader.class.getDeclaredMethod("getClassLoader", Class.class);
				getClassLoader.setAccessible(true);

				return (Class) findLoadedClass.invoke(getClassLoader.invoke(null, Reflection.getCallerClass(2)), fullClassName);
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		});
	}

	private static Class<?> load0(String fullClassName, byte[] javaClassByteArray)
	{
		if (javaClassByteArray != null) {
			final ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
			return AccessController.doPrivileged((PrivilegedAction<Class>) () ->
			{
				try {
					final Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
					defineClass.setAccessible(true);
					return (Class) defineClass.invoke(systemClassLoader, fullClassName, javaClassByteArray, 0, javaClassByteArray.length);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}
			});
		} else {
			return null;
		}
	}
}
