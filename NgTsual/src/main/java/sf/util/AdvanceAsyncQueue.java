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

    class ReadBlock {
        Block block;
        volatile boolean inUse = false;
        final Object lock = new Object();
    }

    private final int rb_size;
    private final static Object NULL = new Object();
    private int cs;
    private Class<T> klass;

    private int rb_index = 0;
    private List<ReadBlock> rb_list;

    private Block cur_write;
    private final Object cur_write_lock = new Object();
    private ConcurrentLinkedQueue<Block> sbq;
    private List<T> rl;

    /**
     * @param klass          klass
     * @param cacheSize      队列块大小
     * @param readBlockCount 读取块大小 建议 读取线程数量/8
     */
    public AdvanceAsyncQueue(Class<T> klass, int cacheSize, int readBlockCount) {
        this.klass = Objects.requireNonNull(klass);
        this.cs = cacheSize > 8 ? cacheSize : 8;
        this.rb_size = readBlockCount;
        init();
    }

    public AdvanceAsyncQueue(Class<T> klass) {
        this.klass = Objects.requireNonNull(klass);
        cs = 16;
        this.rb_size = 16;
        init();
    }

    private void init() {
        cur_write = new Block();
        sbq = new ConcurrentLinkedQueue<>();
        rl = Collections.synchronizedList(new ArrayList<>());
        rb_list = new ArrayList<>(rb_size);
        for (int i = 0; i < rb_size; i++) {
            rb_list.add(new ReadBlock());
        }
    }

    private synchronized ReadBlock nextReadBlock() {
        return rb_list.get(rb_index = ((rb_index + 1) % rb_size));
    }

    @Override
    public T next() {
        ReadBlock readBlock = null;
        for (int i = 0; i < rb_size; i++) {
            readBlock = nextReadBlock();
            if (readBlock.inUse || readBlock.block == null || readBlock.block.array == null || readBlock.block.size == readBlock.block.index) {
            } else break;
        }


        Object cur_obj = NULL;

        synchronized (readBlock.lock) {
            readBlock.inUse = true;
            if (readBlock.block == null) {
                if ((readBlock.block = sbq.poll()) != null) {
                    cur_obj = readBlock.block.array[readBlock.block.index];
                } else if (cur_write.size > 0) {
                    synchronized (cur_write_lock) {
                        readBlock.block = cur_write;
                        cur_write = new Block();
                        cur_obj = readBlock.block.array[readBlock.block.index];
                    }
                }
            } else {
                if (++readBlock.block.index < readBlock.block.size) {
                    cur_obj = readBlock.block.array[readBlock.block.index];
                } else if ((readBlock.block = sbq.poll()) != null) {
                    cur_obj = readBlock.block.array[readBlock.block.index];
                } else if (cur_write.size > 0) {
                    synchronized (cur_write_lock) {
                        readBlock.block = cur_write;
                        cur_write = new Block();
                        cur_obj = readBlock.block.array[readBlock.block.index];
                    }
                }
            }
            readBlock.inUse = false;
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
                sbq.offer(cur_write);
                cur_write = new Block();
            }
            cur_write.array[cur_write.size++] = obj;
        }
    }

    public void add(T[] objs) {
        for (int i = 0, lt = objs.length / cs; i <= lt; i++)
            sbq.offer(new Block(
                    i == lt ? Arrays.copyOfRange(objs, lt * cs, objs.length - 1)
                            : Arrays.copyOfRange(objs, i * cs, (i + 1) * cs - 1)
            ));
    }

    public void remove(T obj) {
        rl.add(obj);
    }
}
