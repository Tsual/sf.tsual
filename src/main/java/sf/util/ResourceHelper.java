package sf.util;

import sf.uds.interfaces.del.runnable.IRun_1;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;

public class ResourceHelper
{
	public static void loadJars(){
		try {
			loadJar("lib");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadJar(String path) throws IOException
	{
		path = path.replace(".", "/");
		Enumeration<URL> urls = ClassLoader.getSystemClassLoader().getResources(path);
		IRun_1<URL> delLoadUrl = new IRun_1<URL>()
		{
			boolean init = false;
			java.lang.reflect.Method method;
			URLClassLoader cl = (URLClassLoader) ClassLoader.getSystemClassLoader();

			@Override
			public void run(URL arg1) throws Exception
			{
				if (!init) {
					method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
					method.setAccessible(true);
					init = true;
				}
				method.invoke(cl, arg1);
			}
		};
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			if ("file".equals(url.getProtocol())) {
				for (File classFile : Objects.requireNonNull((new File(URLDecoder.decode(url.getFile(), "UTF-8"))).listFiles(pathname -> pathname.isDirectory() || pathname.getName().endsWith(".jar")))) {
					if (classFile.isDirectory())
						loadJar(path + "." + classFile.getName());
					else if (classFile.isFile()) {
						try {
							delLoadUrl.run(classFile.toURL());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
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
