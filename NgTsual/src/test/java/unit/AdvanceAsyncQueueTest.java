package unit;

import org.junit.Assert;
import org.junit.Test;
import sf.util.AsyncQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AdvanceAsyncQueueTest {
    @Test(timeout = 3000)
    public void base0() throws InterruptedException {
        final int t_count = 10;
        Thread[] ths = new Thread[t_count];
        List<Integer>[] ths_list = new List[t_count];
        AsyncQueue<Integer> queue = new AsyncQueue<>(Integer.class, 128, 1);

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
                if (next == null) Assert.fail("AsyncQueue.size error");
                g1 += (ths_list[ti].get(wi) - next);
            }
        Assert.assertEquals(0, g1);
    }

    @Test(timeout = 0)
    public void async0() throws InterruptedException {
        AsyncQueue<Integer> queue = new AsyncQueue<>(Integer.class, 32, 4);
        final int ra = 32;
        final int rw = 4;
        Thread[] rat = new Thread[ra];
        Thread[] rwt = new Thread[rw];
        final int[] rac = new int[ra];
        final int[] rwc = new int[rw];


        for (int i = 0; i < ra; i++) {
            int finalI = i;
            rat[i] = new Thread(() -> {
                while (true) {
                    queue.add(0x00);
                    rac[finalI]++;
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
        Thread.sleep(1000);
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

    @Test(timeout = 10000)
    public void benchmark0() throws InterruptedException {
        AsyncQueue<Integer> queue0 = new AsyncQueue<>(Integer.class, 64, 4);
        final int ra = 1;
        final int rw = 16;
        Thread[] rat = new Thread[ra];
        Thread[] rwt = new Thread[rw];
        final int[] rac0 = new int[ra];
        final int[] rwc0 = new int[rw];
        final int[] rac1 = new int[ra];
        final int[] rwc1 = new int[rw];

        ConcurrentLinkedQueue<Integer> queue1 = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < ra; i++) {
            int finalI = i;
            ConcurrentLinkedQueue<Integer> finalQueue1 = queue1;
            rat[i] = new Thread(() -> {
                while (true) {
                    finalQueue1.add(0x00);
                    rac1[finalI]++;
                }
            });
        }

        for (int i = 0; i < rw; i++) {
            int finalI = i;
            ConcurrentLinkedQueue<Integer> finalQueue = queue1;
            rwt[i] = new Thread(() -> {
                while (true) {
                    if (null != finalQueue.poll())
                        rwc1[finalI]++;
                }
            });
        }

        for (Thread t : rwt)
            t.start();
        for (Thread t : rat)
            t.start();
        Thread.sleep(1000);
        try {
            for (Thread t : rat)
                t.stop();
            for (Thread t : rwt)
                t.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        queue1 = null;
        System.gc();
        Thread.sleep(1000);

        for (int i = 0; i < ra; i++) {
            int finalI = i;
            rat[i] = new Thread(() -> {
                while (true) {
//                    Integer[] integers = new Integer[256];
//                    for (int ie = 0; ie < 256; ie++)
//                        integers[ie] = 0x00;
                    queue0.add(0);
                    rac0[finalI] += 1;
                }
            });
        }

        for (int i = 0; i < rw; i++) {
            int finalI = i;
            rwt[i] = new Thread(() -> {
                while (true) {
                    if (null != queue0.next())
                        rwc0[finalI]++;
                }
            });
        }

        for (Thread t : rwt)
            t.start();
        for (Thread t : rat)
            t.start();
        Thread.sleep(1000);
        try {
            for (Thread t : rat)
                t.stop();
            for (Thread t : rwt)
                t.stop();
        } catch (Exception ignored) {
        }


        System.out.println(Arrays.stream(rac0).sum());
        System.out.println(Arrays.stream(rwc0).sum());
        System.out.println(Arrays.stream(rac1).sum());
        System.out.println(Arrays.stream(rwc1).sum());
        Assert.assertTrue(Arrays.stream(rwc0).sum() > Arrays.stream(rwc1).sum());
    }

}
