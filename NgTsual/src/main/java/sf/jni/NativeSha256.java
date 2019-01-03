package sf.jni;

import sf.uds.encrypt.Encryptor;

public class NativeSha256 implements Encryptor {
    @Override
    public native byte[] encrypt(byte[] arr) throws Exception;
}
