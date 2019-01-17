package sf.util;

import sf.uds.common.IAsyncIterable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AsyncQueue<T> implements IAsyncIterable<T> {
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
            size = array.length;
        }
    }

    class ReadBlock {
        Block block;
        ReadBlock next;
        final Object lock = new Object();
    }

    private ReadBlock rb_head;
    private final int rb_size;
    private int cs;
    private Class<T> klass;
    private Block cur_write;
    private final Object cur_write_lock = new Object();
    private ConcurrentLinkedQueue<Block> sbq;

    /**
     * @param klass          klass
     * @param cacheSize      队列块大小
     * @param readBlockCount 读取块大小 建议 读取线程数量/8
     */
    public AsyncQueue(Class<T> klass, int cacheSize, int readBlockCount) {
        this.klass = Objects.requireNonNull(klass);
        this.cs = cacheSize > 8 ? cacheSize : 8;
        this.rb_size = readBlockCount;
        init();
    }

    public AsyncQueue(Class<T> klass) {
        this.klass = Objects.requireNonNull(klass);
        cs = 16;
        this.rb_size = 16;
        init();
    }

    private void init() {
        cur_write = new Block();
        sbq = new ConcurrentLinkedQueue<>();

        ArrayList<ReadBlock> rb_list = new ArrayList<>(rb_size);
        for (int i = 0; i < rb_size; i++) {
            rb_list.add(new ReadBlock());
        }
        for (int i = 0; i < rb_size; i++) {
            rb_list.get(i).next = rb_list.get((i + 1) % rb_size);
        }
        rb_head = rb_list.get(0);
    }

    @Override
    public T next() {
        ReadBlock readBlock = rb_head = rb_head.next;
        synchronized (readBlock.lock) {
            if (readBlock.block == null) {
                if (poll_q(readBlock)) return readBlock.block.array[readBlock.block.index];
            } else {
                if (++readBlock.block.index < readBlock.block.size) {
                    return readBlock.block.array[readBlock.block.index];
                } else if (poll_q(readBlock)) return readBlock.block.array[readBlock.block.index];
            }
        }
        readBlock = rb_head = rb_head.next;
        synchronized (readBlock.lock) {
            if (readBlock.block != null && ++readBlock.block.index < readBlock.block.size) {
                return readBlock.block.array[readBlock.block.index];
            }
        }
        return null;
    }

    private boolean poll_q(ReadBlock readBlock) {
        if ((readBlock.block = sbq.poll()) != null) {
            return true;
        } else if (cur_write.size > 0) {
            synchronized (cur_write_lock) {
                if (cur_write.size > 0) {
                    readBlock.block = cur_write;
                    cur_write = new Block();
                    return true;
                }
            }
        }
        return false;
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

    public void add(T[] obj) {
        sbq.offer(new Block(obj));
    }
}
