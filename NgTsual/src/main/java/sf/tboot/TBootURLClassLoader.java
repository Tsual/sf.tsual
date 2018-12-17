package sf.tboot;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

class TBootURLClassLoader extends URLClassLoader {
    TBootURLClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    TBootURLClassLoader(URL[] urls) {
        super(urls);
    }

    TBootURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }
}
