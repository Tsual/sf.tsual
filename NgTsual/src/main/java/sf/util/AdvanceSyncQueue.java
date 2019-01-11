package sf.util;

import sf.uds.common.OnceIterable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AdvanceSyncQueue<T> implements OnceIterable<T> {
    class Block {
        T[] array = (T[]) Array.newInstance(klass, cacheSize);
        int size = 0;
        int index = 0;
    }

    private final Object block_io_lock = new Object();

    private int cacheSize;
    private T cur_obj;
    private Class<T> klass;
    private Block cur_read_block = new Block();
    private Block cur_write_block = new Block();
    private final Object cur_read_block_lock = new Object();
    private final Object cur_write_block_lock = new Object();
    private ConcurrentLinkedQueue<Block> r_blocks = new ConcurrentLinkedQueue<>();
    private List<T> removedList = Collections.synchronizedList(new ArrayList<>());

    public AdvanceSyncQueue(Class<T> klass, int cacheSize) {
        this.klass = Objects.requireNonNull(klass);
        this.cacheSize = cacheSize > 8 ? cacheSize : 8;
    }

    public AdvanceSyncQueue(Class<T> klass) {
        this.klass = Objects.requireNonNull(klass);
        cacheSize = 16;
    }

    @Override
    public boolean hasNext() throws Exception {
        boolean rc = false;
        synchronized (cur_read_block_lock) {
            if (++cur_read_block.index < cur_read_block.size) {
                cur_obj = cur_read_block.array[cur_read_block.index];
                if (removedList.contains(cur_obj)) {
                    rc = true;
                } else {
                    return true;
                }
            }

            if (r_blocks.size() > 0) {
                cur_read_block = r_blocks.poll();
                cur_obj = cur_read_block.array[cur_read_block.index];
                if (removedList.contains(cur_obj)) {
                    rc = true;
                } else {
                    return true;
                }
            }
            synchronized (cur_write_block_lock) {
                if (cur_write_block.size > 0) {
                    cur_read_block = cur_write_block;
                    cur_write_block = new Block();
                    cur_obj = cur_read_block.array[cur_read_block.index];
                    if (removedList.contains(cur_obj)) {
                        rc = true;
                    } else {
                        return true;
                    }
                }
            }
        }
        if (rc)
            return hasNext();
        else
            return false;
    }

    @Override
    public T next() {
        return cur_obj;
    }

    public void add(T obj) {
        synchronized (cur_write_block_lock) {
            if (cur_write_block.size == cacheSize) {
                r_blocks.offer(cur_write_block);
                cur_write_block = new Block();
            }
            cur_write_block.array[cur_write_block.size++] = obj;
        }
    }

    public void remove(T obj) {
        removedList.add(obj);
    }
}
