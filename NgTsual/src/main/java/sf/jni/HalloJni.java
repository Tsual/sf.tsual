package sf.jni;

import sf.tboot.AutoBootNative;

public class HalloJni extends AutoBootNative {

    public static void main(String[] args) throws Exception {
        foo(new byte[]{0xE}, "", 0, new Object());
        final byte[] encrypt = new NativeAes("123456").encrypt("123456".getBytes());
        int a = 0;
    }

    public native static byte[] foo(byte[] array, String str, int i, Object obj);
}
