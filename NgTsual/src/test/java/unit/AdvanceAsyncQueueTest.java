package unit;

import org.junit.Assert;
import org.junit.Test;
import sf.util.AdvanceAsyncQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AdvanceAsyncQueueTest {
    @Test(timeout = 3000)
    public void base0() throws InterruptedException {
        final int t_count = 10;
        Thread[] ths = new Thread[t_count];
        List<Integer>[] ths_list = new List[t_count];
        AdvanceAsyncQueue<Integer> queue = new AdvanceAsyncQueue<>(Integer.class, 128, 1);

        final int w_count = 10000;
        for (int ti = 0; ti < t_count; ti++) {
            final Random random = new Random();
            ths_list[ti] = new ArrayList<>();
            int finalTi = ti;
            ths[ti] = new Thread(() -> {
                for (int i = 0; i < w_count; i++) {
                    final int i1 = random.nextInt(5000);
                    ths_list[finalTi].add(i1);
                    queue.add(i1);
                }
            });
            ths[ti].start();
        }

        for (Thread t : ths)
            t.join(5000);

        long g1 = 0;
        for (int ti = 0; ti < t_count; ti++)
            for (int wi = 0; wi < w_count; wi++) {
                final Integer next = queue.next();
                if (next == null) Assert.fail("AdvanceAsyncQueue.size error");
                g1 += (ths_list[ti].get(wi) - next);
            }
        Assert.assertEquals(0, g1);
    }

    @Test(timeout = 0)
    public void async0() throws InterruptedException {
        AdvanceAsyncQueue<Integer> queue = new AdvanceAsyncQueue<>(Integer.class, 256, 4);
        final int ra = 1;
        final int rw = 4;
        Thread[] rat = new Thread[ra];
        Thread[] rwt = new Thread[rw];
        final int[] rac = new int[ra];
        final int[] rwc = new int[rw];


        for (int i = 0; i < ra; i++) {
            int finalI = i;
            rat[i] = new Thread(() -> {
                while (true) {
                    Integer[] br = new Integer[256];
                    for (int e = 0; e < 256; e++)
                        br[e] = 0x00;
                    queue.add(br);
                    rac[finalI] += 256;
                }
            });
        }

        for (int i = 0; i < rw; i++) {
            int finalI = i;
            rwt[i] = new Thread(() -> {
                while (true) {
                    if (null != queue.next())
                        rwc[finalI]++;
                }
            });
        }

        for (Thread t : rwt)
            t.start();
        for (Thread t : rat)
            t.start();
        Thread.sleep(10000);
        try {
            for (Thread t : rat)
                t.stop();
            for (Thread t : rwt)
                t.stop();
        } catch (Exception ignored) {
        }
        System.out.println(Arrays.stream(rac).sum());
        System.out.println(Arrays.stream(rwc).sum());

    }

}
