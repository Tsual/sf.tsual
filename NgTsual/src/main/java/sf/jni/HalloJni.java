package sf.jni;

import sf.tboot.ClassLoaderHelper;

import java.io.File;
import java.net.URL;

public class HalloJni {
    static {
        final File file = new File(ClassLoader.getSystemResource("libsf64.dll").getFile());
        ClassLoaderHelper.loadLibrary0(HalloJni.class.getClassLoader(), HalloJni.class, file);
    }

    public static void main(String[] args) {
        System.out.println(foo(new byte[]{0xE}, "", 0, new Object())[0]);
    }

    public native static byte[] foo(byte[] array, String str, int i, Object obj);
}
