package unit;

import org.junit.Test;
import sf.jni.NativeAesCtr;
import sf.util.SimpleAesHelper;
import sf.util.StringHelper;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.IntFunction;

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

    @Test(timeout = 4000)
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
        String s_res = String.valueOf(res);
        if (res < 0)
            Assert.fail(s_res);
        else {
            if (s_res.length() < 9) {
                if (s_res.length() < 8) {
                    char[] append = new char[8 - s_res.length()];
                    Arrays.fill(append, '0');
                    s_res = String.valueOf(append) + s_res;
                }
                s_res = ("0." + s_res).substring(0, 5);
            } else {
                s_res = (s_res.substring(0, s_res.length() - 9) + "." + s_res.substring(s_res.length() - 9)).substring(0, 5);
            }
            System.out.println(s_res);
        }
    }
}