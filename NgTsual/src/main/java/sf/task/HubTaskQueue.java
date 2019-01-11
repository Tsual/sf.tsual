package sf.task;

import java.util.*;

public class HubTaskQueue extends AbsTaskQueue {
    private static final int for_do_ec_count = 4;

    final Map<TaskHub, Queue<Task>> queueMap = new HashMap<>();
    Task[] for_do;
    List<Task> removedList = new ArrayList<>();
    int for_do_index = 0;
    final Object for_do_read_lock = new Object();


    private synchronized boolean pickup() {
        boolean result = false;
        final Set<Map.Entry<TaskHub, Queue<Task>>> entries = queueMap.entrySet();
        synchronized (queueMap) {
            Task[] for_do = new Task[for_do_ec_count * entries.size()];
            int i = 0;
            for (Map.Entry<TaskHub, Queue<Task>> entry : entries) {
                for (int iv = 0; iv < for_do_ec_count; iv++) {
                    synchronized (entry.getValue()) {
                        if (entry.getValue().size() > 0) {
                            for_do[i * for_do_ec_count + iv] = entry.getValue().poll();
                            if (!result) result = true;
                        } else {
                            queueMap.remove(entry.getKey());
                            break;
                        }
                    }
                }
                i++;
            }
        }
        synchronized (for_do_read_lock) {
            for_do_index = 0;
            this.for_do = for_do;
        }
        return result;
    }

    @Override
    public Task element() {
        return for_do[for_do_index];
    }

    @Override
    public void remove(Task task) {
        synchronized (for_do_read_lock) {
            removedList.add(task);
        }
    }

    @Override
    public void add(Task task) {
        synchronized (queueMap) {
            if (queueMap.containsKey(task)){

            }
        }
    }

    @Override
    public boolean remain() {
        if (for_do != null) {
            synchronized (for_do_read_lock) {
                if (for_do_index < for_do.length) {

                }
            }
        }
        return false;
    }
}
