package sf.uds.encrypt;

@FunctionalInterface
public interface Encryptor {
    byte[] encrypt(byte[] arr) throws Exception;
}

