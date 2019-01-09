package sf.learn;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.SecureRandom;

public class NioSocketClient {
    public static void main(String[] args) throws Exception {
        start(12345);
    }

    public static void start(int port) throws Exception {
        try {
            final SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("127.0.0.1", port));
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            SecureRandom sr = new SecureRandom();
            sr.nextBytes(byteBuffer.array());
            byteBuffer.rewind();
            sc.write(byteBuffer);
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
