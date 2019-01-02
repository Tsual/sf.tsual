package sf.jni;

import sf.uds.interfaces.encrypt.Interchanger;

public class NativeSha256 implements Interchanger {
    @Override
    public native byte[] decrypt(byte[] arr) throws Exception;

    @Override
    public native byte[] encrypt(byte[] arr) throws Exception;
}
