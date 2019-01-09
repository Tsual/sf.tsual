package sf.learn;

import sf.task.TaskHost;
import sf.task.TaskHub;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
            ByteBuffer[] bba = null;
            accept.read(bba);
            for (ByteBuffer receive : bba) {
                System.out.print(receive.array().length + "<<");
                System.out.println(Base64.getEncoder().encodeToString(receive.array()));
            }
            accept.finishConnect();
        }
    }

    public static void main(String[] args) throws Exception {
        final NioSocketServer nioSocketServer = new NioSocketServer(12345);
        nioSocketServer.start_server();
    }
}
