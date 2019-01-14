package sf.explore;

import sf.task.TaskHost;
import sf.task.TaskHub;
import sf.task.ThreadLocalOperation;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Base64;

public class NioSocketServer {
    private int port;
    private TaskHost host;
    private TaskHub hub;

    public NioSocketServer(int port) {
        this.port = port;
        host = new TaskHost("NioSocketServer", 50, 200, 50L);
        hub = host.newTaskHub(50L, null);
    }

    public void start_server() throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", port));
        while (true) {
            final SocketChannel accept = ssc.accept();
            hub.execute(() -> {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int len;
                String base64 = "";
                while ((len = accept.read(byteBuffer)) > 0) {
                    base64 += Base64.getEncoder().encodeToString(len == 1024 ? byteBuffer.array() : Arrays.copyOf(byteBuffer.array(), len));
                    byteBuffer.clear();
                }
                System.out.println(System.currentTimeMillis() + "<<" + base64);
                accept.close();
                return true;
            }, ThreadLocalOperation.Reset).awaitResultClose();
        }
    }

    public static void main(String[] args) throws Exception {
        final NioSocketServer nioSocketServer = new NioSocketServer(12345);
        nioSocketServer.start_server();
    }
}
