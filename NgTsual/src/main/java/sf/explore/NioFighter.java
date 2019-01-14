package sf.explore;

import sf.task.DefaultHost;
import sf.task.TaskHub;
import sf.task.ThreadLocalOperation;

import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.security.SecureRandom;
import java.util.*;

public class NioFighter {
    static int total = 30;
    final static int[] count_a;
    final static Object[] count_lock_a;
    final static SocketChannel[] client_a;
    final static String port = "12345";

    static {
        client_a = (SocketChannel[]) Array.newInstance(SocketChannel.class, total);
        count_a = (int[]) Array.newInstance(int.class, total);
        count_lock_a = (Object[]) Array.newInstance(Object.class, total);
    }

    public static void main(String[] args) throws Exception {
        final TaskHub taskHub = DefaultHost.newDefaultHub(System.out::println);
        taskHub.execute(() -> {
            NioFighter.start_server(port);
            return null;
        }, ThreadLocalOperation.None);
        for (int i = 0; i < total; i++) {
            try {
                count_a[i] = 0;
                count_lock_a[i] = new Object();
                client_a[i] = SocketChannel.open();
                client_a[i].connect(new InetSocketAddress("127.0.0.1", Integer.parseInt(port)));
                client_a[i].configureBlocking(true);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            int finalI = i;
            taskHub.execute(() -> {
                while (true)
                    NioFighter.start_client(port, finalI);
            }, ThreadLocalOperation.None);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    int total_count = 0;
                    for (int i = 0; i < total; i++) {
                        synchronized (count_lock_a[i]) {
                            total_count += count_a[i];
                            count_a[i] = 0;
                        }
                    }
                    System.out.print(total_count);
                    System.out.println(taskHub.trackWorker());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000, 1000);

        taskHub.waitAll();
    }

    public static void start_server(String port) {
        try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", Integer.parseInt(port)));
            ssc.configureBlocking(false);

            final Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            final ByteBuffer receive_buffer = ByteBuffer.allocate(1024);
            final ByteBuffer send_buffer = ByteBuffer.allocate(128);
            SecureRandom secureRandom = new SecureRandom();

            while (true) {
                selector.select();
                final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    final SelectionKey skey = iterator.next();
                    iterator.remove();
                    if (skey.isAcceptable()) {
                        SocketChannel socketChannel = ssc.accept();
                        if (socketChannel != null) {
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                    } else if (skey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) skey.channel();
                        receive_buffer.clear();
                        socketChannel.read(receive_buffer);
                        receive_buffer.flip();
                        skey.interestOps(SelectionKey.OP_WRITE);
                    } else if (skey.isWritable()) {
                        secureRandom.nextBytes(send_buffer.array());
                        send_buffer.rewind();
                        SocketChannel socketChannel = (SocketChannel) skey.channel();
                        socketChannel.write(send_buffer);
                        skey.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void start_client(String port, int index) {
        SocketChannel client = client_a[index];
        try {
            ByteBuffer send_buffer = ByteBuffer.allocate(128);
            ByteBuffer receive_buffer = ByteBuffer.allocate(128);
            send_buffer.put("hello".getBytes());
            send_buffer.flip();
            send_buffer.rewind();
            client.write(send_buffer);
            receive_buffer.clear();
            client.read(receive_buffer);
            synchronized (count_lock_a[index]) {
                count_a[index]++;
            }
            //client.finishConnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
