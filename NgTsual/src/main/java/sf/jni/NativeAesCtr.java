package sf.jni;

import sf.tboot.AutoBootNative;
import sf.uds.interfaces.encrypt.Interchanger;

public class NativeAesCtr extends AutoBootNative implements Interchanger {
    private final String key;

    public NativeAesCtr(String key) {
        this.key = key;
    }

    public final String getKey() {
        return key;
    }

    @Override
    public native final byte[] decrypt(byte[] arr) throws Exception;

    @Override
    public native final byte[] encrypt(byte[] arr) throws Exception;
}
