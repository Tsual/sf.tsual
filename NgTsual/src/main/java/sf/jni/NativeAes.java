package sf.jni;

import sf.tboot.AutoBootNative;
import sf.uds.interfaces.encrypt.Interchanger;

public class NativeAes extends AutoBootNative implements Interchanger {
    private final String key;

    public NativeAes(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public native byte[] decrypt(byte[] arr) throws Exception;

    @Override
    public native byte[] encrypt(byte[] arr) throws Exception;
}
