package sf.resource;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;

public class ResourceHelper
{
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
