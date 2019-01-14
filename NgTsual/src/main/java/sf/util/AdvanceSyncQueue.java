package sf.util;

import sf.uds.common.OnetimeIterable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AdvanceSyncQueue<T> implements OnetimeIterable<T> {
    class Block {
        T[] array;
        int size;
        int index = 0;

        Block() {
            array = (T[]) Array.newInstance(klass, cacheSize);
            size = 0;
        }

        Block(T[] array) {
            this.array = array;
            this.size = array.length;
        }
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
    public boolean hasNext() {
        synchronized (cur_read_block_lock) {
            if (++cur_read_block.index < cur_read_block.size) {
                cur_obj = cur_read_block.array[cur_read_block.index];
                if (removedList.remove(cur_obj)) {
                    return hasNext();
                } else {
                    return true;
                }
            }

            if (r_blocks.size() > 0) {
                cur_read_block = r_blocks.poll();
                cur_obj = cur_read_block.array[cur_read_block.index];
                if (removedList.remove(cur_obj)) {
                    return hasNext();
                } else {
                    return true;
                }
            }
            synchronized (cur_write_block_lock) {
                if (cur_write_block.size > 0) {
                    cur_read_block = cur_write_block;
                    cur_write_block = new Block();
                    cur_obj = cur_read_block.array[cur_read_block.index];
                    if (removedList.remove(cur_obj)) {
                        return hasNext();
                    } else {
                        return true;
                    }
                }
            }
        }
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

    public void add(T[] objs) {
        for (int i = 0, lt = objs.length / cacheSize; i <= lt; i++)
            r_blocks.offer(new Block(
                    i == lt ? Arrays.copyOfRange(objs, lt * cacheSize, objs.length - 1)
                            : Arrays.copyOfRange(objs, i * cacheSize, (i + 1) * cacheSize - 1)
            ));
    }

    public void remove(T obj) {
        removedList.add(obj);
    }
}
