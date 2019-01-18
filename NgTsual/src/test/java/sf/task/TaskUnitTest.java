package sf.task;

import org.junit.Assert;
import org.junit.Test;
import sf.task.TaskHost;
import sf.task.TaskHub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskUnitTest {
    @Test(timeout = 4000)
    public void base0() {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        final int max_ths_count = 60;
        try (TaskHost taskHost = new TaskHost("host", 5, max_ths_count, 50L)) {
            final TaskHub taskHub = taskHost.newTaskHub(150L, null);
            int count = 100000;
            for (int i = 0; i < count; i++) {
                taskHub.execute(() -> {
                    list.add(0x0);
                    Thread.sleep(1);
                    return null;
                });
            }
            taskHub.waitAll();
            Assert.assertEquals(list.size(), count);
            Assert.assertEquals(max_ths_count, taskHost.thread_group.activeCount());
        }
    }

    @Test(timeout = 3000)
    public void base1() {
        try (TaskHost host = new TaskHost("host", 5, 10, 50L)) {
            final TaskHub taskHub = host.newTaskHub(150L, null);
            final TaskHub taskHub0 = host.newTaskHub(150L, null);
            for (int i = 0; i < 5000; i++)
                taskHub0.execute(() -> {
                    Thread.sleep(50000);
                    return null;
                });
            final boolean[] deal = {false};
            taskHub.execute(() -> deal[0] = true);
            taskHub.waitJump(1000);
            Assert.assertTrue(deal[0]);
        }
    }
}
