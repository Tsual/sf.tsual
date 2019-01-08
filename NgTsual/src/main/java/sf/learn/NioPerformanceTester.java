package sf.learn;

import sf.task.Task;
import sf.task.TaskHost;
import sf.task.TaskHub;
import sf.task.ThreadLocalOperation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.SecureRandom;
import java.util.*;

public class NioPerformanceTester {
    public final String localPort;
    public int clientCount;
    public final int[] clientRec;
    public final SocketChannel[] clientConnect;
    public final ByteBuffer[] clientSendBuffer;
    public final ByteBuffer[] clientReceiveBuffer;
    public volatile boolean quit = false;
    public ServerSocketChannel ssc = ServerSocketChannel.open();

    public NioPerformanceTester(String localPort, int clientCount) throws IOException {
        this.localPort = localPort;
        this.clientCount = clientCount;
        clientConnect = (SocketChannel[]) Array.newInstance(SocketChannel.class, clientCount);
        clientRec = (int[]) Array.newInstance(int.class, clientCount);
        clientSendBuffer = new ByteBuffer[clientCount];
        clientReceiveBuffer = new ByteBuffer[clientCount];
    }

    private void start_server(String port) {
        try {
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

    private void start_client(String port, int index) {
        try {
            clientSendBuffer[index].rewind();
            clientConnect[index].write(clientSendBuffer[index]);
            clientReceiveBuffer[index].clear();
            clientConnect[index].read(clientReceiveBuffer[index]);
            clientRec[index]++;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Report test(int second) throws IOException {
        UUID uuid = UUID.randomUUID();
        final TaskHost nioTesterHost = new TaskHost("NioTester-" + uuid, clientCount + 5, clientCount + 10, 50L);
        final TaskHub taskHub = nioTesterHost.newTaskHub(150L, null);
        final TaskHub taskHub0 = nioTesterHost.newTaskHub(150L, null);

        Report report = new Report();
        report.client = clientCount;
        report.second = second;
        report.record = new int[second];

        taskHub0.execute(() -> {
            start_server(localPort);
            return null;
        }, ThreadLocalOperation.None);

        for (int i = 0; i < clientCount; i++) {
            try {
                clientRec[i] = 0;
                clientConnect[i] = SocketChannel.open();
                clientConnect[i].connect(new InetSocketAddress("127.0.0.1", Integer.parseInt(localPort)));
                clientConnect[i].configureBlocking(true);
                clientSendBuffer[i] = ByteBuffer.allocate(128);
                clientReceiveBuffer[i] = ByteBuffer.allocate(128);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < clientCount; i++) {
            int finalI = i;
            taskHub.execute(() -> {
                while (!quit)
                    start_client(localPort, finalI);
                return "client-" + finalI;
            }, ThreadLocalOperation.None);
        }


        final Timer timer = new Timer();
        final int[] cm = {0, 0};
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (cm[0] != second) {
                        cm[0]++;
                        int total_count = 0;
                        for (int i = 0; i < clientCount; i++) {
                            total_count += clientRec[i];
                        }
                        final int cur_total = total_count - cm[1];
                        report.record[cm[0] - 1] = cur_total;
                        //System.out.println(cur_total);
                        if (cur_total > report.max)
                            report.max = cur_total;
                        cm[1] = total_count;
                        if (cm[0] == second) {
                            report.avg = total_count / cm[0];
                            quit = true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000, 1000);
        taskHub.waitAll();

        for (SocketChannel sc : clientConnect) {
            sc.finishConnect();
        }
        ssc.close();

        timer.cancel();
        return report;
    }


    public static class Report {
        public int max = 0;
        public int avg = 0;
        public int client = 0;
        public int second = 0;
        public int[] record;

        @Override
        public String toString() {
            return "Report{" +
                    "max=" + max +
                    ", avg=" + avg +
                    ", client=" + client +
                    ", second=" + second +
                    ", record=" + Arrays.toString(record) +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new NioPerformanceTester("12345", 10).test(5));
        System.out.println(new NioPerformanceTester("12346", 20).test(5));
        System.out.println(new NioPerformanceTester("12347", 24).test(5));
    }
}
