package unit;

import org.junit.Assert;
import org.junit.Test;
import sf.util.AdvanceAsyncQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdvanceAsyncQueueTest {
    @Test(timeout = 3000)
    public void base0() throws InterruptedException {
        final int t_count = 10;
        Thread[] ths = new Thread[t_count];
        List<Integer>[] ths_list = new List[t_count];
        AdvanceAsyncQueue<Integer> queue = new AdvanceAsyncQueue<>(Integer.class, 128);

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

    @Test(timeout = 4000)
    public void async0() throws InterruptedException {
        AdvanceAsyncQueue<Integer> queue = new AdvanceAsyncQueue<>(Integer.class, 128);
        final int t_count = 10;
        Thread[] ths = new Thread[t_count * 3];
        for (int ti = 0; ti < t_count; ti++) {
            ths[ti] = new Thread(() -> {
                Random random = new Random();
                while (true) queue.add(random.nextInt());
            });
            ths[ti + t_count] = new Thread(() -> {
                while (true) queue.next();
            });
            ths[ti + t_count * 2] = new Thread(() -> {
                while (true) queue.next();
            });
        }
        for (Thread t : ths)
            t.start();
        Thread.sleep(3000);
        try {
            for (Thread t : ths)
                t.stop();
        } catch (Exception ignored) {
        }
    }

}
