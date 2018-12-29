package sf.jni;

import sf.tboot.AutoBootNative;

public class HalloJni extends AutoBootNative {

    public static void main(String[] args) throws Exception {
        //foo(new byte[]{0xE}, "", 0, new Object());
        final NativeAesCtr nativeAesCtr = new NativeAesCtr("123456");
        final byte[] bytes = "1234fsdafasdfaffdasfd56".getBytes();
        final byte[] encrypt = nativeAesCtr.encrypt(bytes);
        final byte[] decrypt = nativeAesCtr.decrypt(encrypt);
        int a = 0;
    }

    public native static byte[] foo(byte[] array, String str, int i, Object obj);
}
