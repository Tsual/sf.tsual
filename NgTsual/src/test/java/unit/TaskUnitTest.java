package unit;

import org.junit.Assert;
import org.junit.Test;
import sf.task.TaskHost;
import sf.task.TaskHub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskUnitTest {
    @Test
    public void base0() {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        try (TaskHost th = new TaskHost("ut-0", 5, 10, 50L)) {
            final TaskHub taskHub = th.newTaskHub(150L, null);
            int count = 100000;
            for (int i = 0; i < count; i++)
                taskHub.execute(() -> list.add(0x0));
            taskHub.waitAll();
            Assert.assertEquals(list.size(), count);
        }
    }
}
