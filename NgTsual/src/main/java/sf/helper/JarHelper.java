package sf.helper;

import sf.uds.del.IRun_1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Objects;

public class JarHelper
{
	public static void loadJar(String path)
	{
		try {
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
								delLoadUrl.run(classFile.toURI().toURL());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
