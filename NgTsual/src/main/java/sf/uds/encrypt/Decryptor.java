package sf.uds.encrypt;

@FunctionalInterface
public interface Decryptor {
    byte[] decrypt(byte[] arr) throws Exception;
}
