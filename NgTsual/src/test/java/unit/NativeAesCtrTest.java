package unit;

import org.junit.Test;
import sf.jni.NativeAesCtr;
import sf.util.SimpleAesHelper;
import sf.util.StringHelper;
import org.junit.Assert;

public class NativeAesCtrTest {
    @Test
    public void nativeCtrFunction() throws Exception {
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

    @Test
    public void performance() throws Exception {
        int count = 100;
        final byte[] bytes = StringHelper.randomString(5000).getBytes();
        final String key = StringHelper.randomString(20);
        final NativeAesCtr nativeAesCtr = new NativeAesCtr(key);
        final SimpleAesHelper simpleAesHelper = new SimpleAesHelper(key.getBytes());

        long res = 0;
        while (count-- > 0) {
            final long l1 = System.nanoTime();
            nativeAesCtr.encrypt(bytes);
            final long l2 = System.nanoTime();
            simpleAesHelper.encrypt(bytes);
            final long l3 = System.nanoTime();
            res += (l3 - l2 - l2 + l1);
        }
        if (res < 0)
            Assert.fail(String.valueOf(res));
    }
}