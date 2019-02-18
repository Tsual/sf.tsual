package demo;

import io.netty.bootstrap.ServerBootstrap;

public class DemoServer {
    public static void main(String[] args) throws InterruptedException {
        new DiscardServer(1314).run();
    }
}
