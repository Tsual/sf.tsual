package sf.jni;

import sf.tboot.AutoBootNative;

public class HalloJni extends AutoBootNative {

    public static void main(String[] args) {
        System.out.println(foo(new byte[]{0xE}, "", 0, new Object())[0]);
    }

    public native static byte[] foo(byte[] array, String str, int i, Object obj);
}
