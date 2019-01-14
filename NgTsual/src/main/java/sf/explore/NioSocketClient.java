package sf.explore;

import sf.task.Task;
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
        try (TaskHost host = new TaskHost("NioSocketClient", 50, 100, 50L)) {
            while (true) {
                TaskHub hub = host.newTaskHub(50L, null);
                try {
                    int count = 5;
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
                try {
                    for (Task task : hub.getTasks())
                        task.getResult();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
