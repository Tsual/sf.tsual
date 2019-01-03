package unit;

import org.junit.Test;
import sf.jni.NativeAesCtr;
import sf.util.StringHelper;
import org.junit.Assert;

public class NativeAesCtrTest {
    @Test
    public void testNativeAes() throws Exception {
        int count = 10;
        while (count-- > 0) {
            final byte[] bytes = StringHelper.randomString(50).getBytes();
            final String key = StringHelper.randomString(20);
            NativeAesCtr nativeAesCtr = new NativeAesCtr(key);
            final byte[] encrypt = nativeAesCtr.encrypt(bytes);
            final byte[] decrypt = nativeAesCtr.decrypt(encrypt);
            Assert.assertEquals(encrypt.length, bytes.length);
            Assert.assertArrayEquals(bytes, decrypt);
        }
    }
}