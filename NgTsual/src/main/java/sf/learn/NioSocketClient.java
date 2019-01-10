package sf.learn;

import sf.task.TaskHost;
import sf.task.TaskHub;
import sf.task.ThreadLocalOperation;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.SecureRandom;

public class NioSocketClient {
    public static void main(String[] args) throws Exception {
        start(12345);
    }

    public static void start(int port) throws Exception {
        try (TaskHost host = new TaskHost("NioSocketClient", 5, 6, 50L)) {
            TaskHub hub = host.newTaskHub(50L, System.out::println);
            try {
                int count = 100;
                while (count-- > 0) {
                    hub.execute(() -> {
                        final SocketChannel sc = SocketChannel.open();
                        sc.connect(new InetSocketAddress("127.0.0.1", port));
                        ByteBuffer byteBuffer = ByteBuffer.allocate(5555);
                        SecureRandom sr = new SecureRandom();
                        sr.nextBytes(byteBuffer.array());
                        byteBuffer.rewind();
                        sc.write(byteBuffer);
                        sc.close();
                        return true;
                    }, ThreadLocalOperation.None);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            hub.waitAll();
        }
    }
}
