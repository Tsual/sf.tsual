package sf.tboot;


import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TbootClassLoader extends ClassLoader
{
	public Class<?> register(String fullClassName, File javaCodeFile)
	{
		final int BUFFER_SIZE = 1024;
		try {
			if (javaCodeFile != null && javaCodeFile.isFile()) {
				final ArrayList<byte[]> list = new ArrayList<>();
				try (FileInputStream fis = new FileInputStream(javaCodeFile)) {
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
					return register(fullClassName, data);
				}
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public Class<?> register(String fullClassName, byte[] javaCodeByteArray)
	{
		if (javaCodeByteArray != null) {
			return defineClass(fullClassName, javaCodeByteArray, 0, javaCodeByteArray.length);
		} else {
			return null;
		}
	}

	public Class<?> register(String fullClassName, String javaCode)
	{
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

			final JavaFileManager file_manager = new ForwardingJavaFileManager(compiler.getStandardFileManager(null, null, null))
			{
				@Override
				public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling)
				{
					return new SimpleJavaFileObject(URI.create("string:///" + className.replace('.', '/') + kind.extension), kind)
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

			final URI source_file_object_uri = URI.create("string:///" + fullClassName.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension);
			final JavaFileObject source_file_object = new SimpleJavaFileObject(source_file_object_uri, JavaFileObject.Kind.SOURCE)
			{
				@Override
				public CharSequence getCharContent(boolean ignoreEncodingErrors)
				{
					return javaCode;
				}
			};

			if (compiler.getTask(null, file_manager, null, compile_param, null, Collections.singletonList(source_file_object)).call()) {
				return register(fullClassName, bos.toByteArray());
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public Class<?> register(String fullClassName, Buffer javaCodeNioBuffer)
	{
		if (javaCodeNioBuffer.hasArray())
			return register(fullClassName, (byte[]) javaCodeNioBuffer.array());
		else return null;
	}
}
