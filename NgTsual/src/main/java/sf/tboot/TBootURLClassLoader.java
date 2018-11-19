package sf.tboot;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class TBootURLClassLoader extends URLClassLoader
{
	public TBootURLClassLoader(URL[] urls, ClassLoader parent)
	{
		super(urls, parent);
	}

	public TBootURLClassLoader(URL[] urls)
	{
		super(urls);
	}

	public TBootURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory)
	{
		super(urls, parent, factory);
	}
}
