package sf.tboot;

import sf.tquery.ArrayIterable;
import sf.tquery.Iterator;
import sf.tquery.Iterators;
import sf.uds.del.IExec_0;
import sf.util.EntryBean;
import sf.util.MapHelper;

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
            new EntryBean<>(SUPPORT_OS.WINDOWS_64, () -> AccessController.doPrivileged((PrivilegedAction<Boolean>) () -> "windows".equals(System.getProperty("sun.desktop")) && "x64".equals(System.getProperty("os.arch")))),
            new EntryBean<>(SUPPORT_OS.WINDOWS_32, () -> AccessController.doPrivileged((PrivilegedAction<Boolean>) () -> "windows".equals(System.getProperty("sun.desktop")) && "x86".equals(System.getProperty("os.arch"))))
    ));

    private final static Map<SUPPORT_OS, File[]> OsLibMapping = Collections.unmodifiableMap(MapHelper.getMapWithEntry(
            new EntryBean<>(SUPPORT_OS.WINDOWS_64, new File[]{new File("E:\\Proj\\sf.tsual\\jni\\x64\\Release\\libsf_win_x64.dll")}),
            new EntryBean<>(SUPPORT_OS.WINDOWS_32, new File[]{new File("E:\\Proj\\sf.tsual\\jni\\Release\\Win32\\libsf_win_Win32.dll")})
    ));

    static {
        try {
            final Iterator<File> oit = Iterators.get(OsNameMapping.entrySet())
                    .where(arg1 -> arg1.getValue().execute())
                    .map(arg1 -> new ArrayIterable<>(OsLibMapping.get(arg1.getKey())));

            switch (oit.toList().size()) {
                case 0:
                    throw new RuntimeException("Os not support");
                default:
                    oit.foreach(arg1 -> {
                        final ClassLoader classLoader = AutoBootNative.class.getClassLoader();
                        ClassLoaderHelper.loadLibrary0(classLoader, AutoBootNative.class, arg1);
                    });
                    break;
            }
        } catch (RuntimeException ignored) {
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
