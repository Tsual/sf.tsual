package sf.uds.util.encrypt;

import sf.uds.interfaces.encrypt.Interchanger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class SimpleAes implements Interchanger
{
	byte[] key;

	public SimpleAes(byte[] key)
	{
		this.key = key;
	}

	@Override
	public byte[] decrypt(byte[] arr) throws Exception
	{
		return doWork(arr, Cipher.DECRYPT_MODE);
	}

	@Override
	public byte[] encrypt(byte[] arr) throws Exception
	{
		return doWork(arr, Cipher.ENCRYPT_MODE);
	}

	private byte[] doWork(byte[] arr, int mode) throws Exception
	{
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(key));
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(mode, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		return cipher.doFinal(arr);
	}
}
