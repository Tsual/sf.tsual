package sf.learn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Random;

public class NioFighter {
    public static void main(String[] args) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(30);
        final WritableByteChannel wbc = Channels.newChannel(baos);
        int count = 10;
        ByteBuffer buf = ByteBuffer.allocate(128 * count);
        while (count-- > 0) {
            byte[] btar = new byte[128];
            new Random().nextBytes(btar);
            buf.put(btar);
        }
        final int write = wbc.write(buf);


        final byte[] bytes = baos.toByteArray();
        int a = 0;
    }
}
