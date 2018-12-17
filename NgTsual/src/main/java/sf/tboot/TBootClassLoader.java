/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tboot;

import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.util.*;

public class TBootClassLoader extends ClassLoader {
    private final List<TBootURLClassLoader> def_slaves = Collections.synchronizedList(new ArrayList<>());
    private final Map<String, Class> tup_map = Collections.synchronizedMap(new HashMap<>());
    private final URLClassLoader app_slave_loader;

    public TBootClassLoader() {
        super(null);
        app_slave_loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (tup_map.containsKey(name))
            return tup_map.get(name);
        else {
            final ClassLoader slave = catch_undefine_slave(name);
            final Class<?> aClass = ClassLoaderHelper.findClass(slave, name);
            tup_map.put(name, aClass);
            return aClass;
        }
    }

    private Class boot0(String name, Class clazz) {
        tup_map.put(name, clazz);
        return clazz;
    }

    Class bootClass(String name, byte[] buffer) {
        return boot0(name, ClassLoaderHelper.defineClass(catch_undefine_slave(name), name, buffer, 0, buffer.length));
    }

    Class bootClass(String name, ByteBuffer buffer) {
        return boot0(name, ClassLoaderHelper.defineClass(catch_undefine_slave(name), name, buffer));
    }

    private ClassLoader catch_undefine_slave(String name) {
        for (TBootURLClassLoader clt : def_slaves) {
            boolean fr = true;
            for (Class clazz_t : ClassLoaderHelper.getFieldClasses(clt))
                if (clazz_t.getName().equals(name)) {
                    fr = false;
                    break;
                }
            if (fr) return clt;
        }
        final TBootURLClassLoader tBootURLClassLoader = new TBootURLClassLoader(app_slave_loader.getURLs(), this);
        def_slaves.add(tBootURLClassLoader);
        return tBootURLClassLoader;
    }
}
