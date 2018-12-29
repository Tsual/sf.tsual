package unit;

import org.junit.Test;
import sf.tquery.Iterators;
import org.junit.Assert;

import java.util.Random;

public class TqueryUnitTest {
    @Test
    public void where() throws Exception {
        int test_count = 10;
        while (test_count-- > 0) {
            Random random = new Random();
            byte[] btar = new byte[Math.abs(random.nextInt(15)) + 1];
            random.nextBytes(btar);
            byte[] bm = new byte[1];
            random.nextBytes(bm);
            Byte[] btoar = new Byte[btar.length];

            byte mark0 = bm[0];
            int java_for_count = 0;
            for (int i = 0; i < btar.length; i++) {
                if (btar[i] > mark0)
                    java_for_count++;
                btoar[i] = btar[i];
            }

            Byte mark1 = bm[0];
            int size = Iterators.get(btoar)
                    .where(arg1 -> arg1 > mark1)
                    .toList().size();
            if (java_for_count != size)
                java_for_count = java_for_count;
            Assert.assertEquals(java_for_count, size);
        }
    }

    @Test
    public void add() throws Exception {
        Random random = new Random();
        byte[] bm = new byte[1];
        random.nextBytes(bm);
        int java_for_count = 0;

        byte[] btar0 = new byte[Math.abs(random.nextInt(5000)) + 1];
        Byte[] btoar0 = new Byte[btar0.length];
        random.nextBytes(btar0);

        for (int i = 0; i < btar0.length; i++) {
            if (btar0[i] > bm[0])
                java_for_count++;
            btoar0[i] = btar0[i];
        }

        byte[] btar1 = new byte[Math.abs(random.nextInt(5000)) + 1];
        Byte[] btoar1 = new Byte[btar1.length];
        random.nextBytes(btar1);

        for (int i = 0; i < btar1.length; i++) {
            if (btar1[i] > bm[0])
                java_for_count++;
            btoar1[i] = btar1[i];
        }

        final int size = Iterators.get(btoar0)
                .add(btoar1)
                .where(arg1 -> arg1 > bm[0])
                .toList()
                .size();
        Assert.assertEquals(java_for_count, size);
    }
}
