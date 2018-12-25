package sf.tboot;

import sf.tquery.ArrayIterable;
import sf.tquery.Iterator;
import sf.tquery.Iterators;
import sf.uds.interfaces.del.IExec_0;
import sf.uds.util.EntryBean;
import sf.uds.util.MapHelper;

import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.Map;

public abstract class AutoBootNative {
    private enum SUPPORT_OS {
        MAC_64, WINDOWS_32, WINDOWS_64, LINUX_32, LINUX_64
    }

    private final static Map<SUPPORT_OS, IExec_0<Boolean>> OsNameMapping = Collections.unmodifiableMap(MapHelper.getMapWithEntry(
            new EntryBean<SUPPORT_OS, IExec_0<Boolean>>(SUPPORT_OS.WINDOWS_64, () -> AccessController.doPrivileged((PrivilegedAction<Boolean>) () -> "windows".equals(System.getProperty("sun.desktop")) && "amd64".equals(System.getProperty("sun.cpu.isalist"))))
    ));

    private final static Map<SUPPORT_OS, String> OsLibMapping = Collections.unmodifiableMap(MapHelper.getMap(
            new SUPPORT_OS[]{SUPPORT_OS.WINDOWS_64},
            new String[]{"cryptopp.dll,libsf_win_x64.dll"}
    ));

    static {
        try {
            final Iterator<String> oit = Iterators.get(OsNameMapping.entrySet())
                    .where(arg1 -> arg1.getValue().execute())
                    .map(arg1 -> new ArrayIterable<>(OsLibMapping.get(arg1.getKey()).split(",")));

            switch (oit.toList().size()) {
                case 0:
                    throw new RuntimeException("Os not support");
                default:
                    oit.foreach(arg1 -> {
                        final ClassLoader classLoader = AutoBootNative.class.getClassLoader();
                        ClassLoaderHelper.loadLibrary0(classLoader, AutoBootNative.class, new File(classLoader.getResource(arg1).getFile()));
                    });
                    break;
            }
        } catch (RuntimeException ignored) {
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
