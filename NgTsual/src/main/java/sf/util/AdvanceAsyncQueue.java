package sf.util;

import sf.uds.common.IOnetimeAsyncIterable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AdvanceAsyncQueue<T> implements IOnetimeAsyncIterable<T> {
    class Block {
        T[] array;
        int size;
        int index = 0;

        Block() {
            array = (T[]) Array.newInstance(klass, cs);
            size = 0;
        }

        Block(T[] array) {
            this.array = array;
            this.size = array.length;
        }
    }

    private final static Object NULL = new Object();
    private int cs;
    private Class<T> klass;
    private Block cur_read, cur_write;
    private final Object cur_read_lock = new Object();
    private final Object cur_write_lock = new Object();
    private ConcurrentLinkedQueue<Block> rbs;
    private List<T> rl;

    public AdvanceAsyncQueue(Class<T> klass, int cacheSize) {
        this.klass = Objects.requireNonNull(klass);
        this.cs = cacheSize > 8 ? cacheSize : 8;
        init();
    }

    public AdvanceAsyncQueue(Class<T> klass) {
        this.klass = Objects.requireNonNull(klass);
        cs = 16;
        init();
    }

    private void init() {
        cur_read = new Block();
        cur_write = new Block();
        rbs = new ConcurrentLinkedQueue<>();
        rl = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public T next() {
        Object cur_obj = NULL;
        synchronized (cur_read_lock) {
            if (++cur_read.index < cur_read.size) {
                cur_obj = cur_read.array[cur_read.index];
            } else if (rbs.size() > 0) {
                cur_read = rbs.poll();
                cur_obj = cur_read.array[cur_read.index];
            } else if (cur_write.size > 0) {
                synchronized (cur_write_lock) {
                    cur_read = cur_write;
                    cur_write = new Block();
                    cur_obj = cur_read.array[cur_read.index];
                }
            }
        }

        if (NULL.equals(cur_obj))
            return null;
        else if (rl.remove(cur_obj)) {
            return next();
        } else {
            return (T) cur_obj;
        }
    }

    public void add(T obj) {
        synchronized (cur_write_lock) {
            if (cur_write.size == cs) {
                rbs.offer(cur_write);
                cur_write = new Block();
            }
            cur_write.array[cur_write.size++] = obj;
        }
    }

    public void add(T[] objs) {
        for (int i = 0, lt = objs.length / cs; i <= lt; i++)
            rbs.offer(new Block(
                    i == lt ? Arrays.copyOfRange(objs, lt * cs, objs.length - 1)
                            : Arrays.copyOfRange(objs, i * cs, (i + 1) * cs - 1)
            ));
    }

    public void remove(T obj) {
        rl.add(obj);
    }
}
