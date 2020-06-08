package com.yanhua.javabase.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NIOServer {
    static int BUF_SIZE = 1024;
    static int PORT = 10086;
    static int TIME_OUT = 3000;

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(PORT));
        serverChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(TIME_OUT) == 0) {
                System.out.println("等待接收...");
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    handleAccept(key);
                } else if (key.isReadable()) {
                    handleRead(key);
                } else if (key.isWritable() && key.isValid()) {
                    handleWrite(key);
                } else if (key.isConnectable()) {
                    System.out.println("isConnectable = true");
                }
                iterator.remove();
            }
        }
    }

    private static void handleAccept(SelectionKey key) throws IOException {
        System.out.println("收到连接...");
        ServerSocketChannel severChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = severChannel.accept();
        channel.configureBlocking(false);
        channel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(BUF_SIZE));

    }

    private static void handleRead(SelectionKey key) throws IOException {
        System.out.println("准备读取 ...");
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();

        long bytesRead = channel.read(byteBuffer);
        StringBuffer sb = new StringBuffer();
        while (bytesRead > 0) {
            byteBuffer.flip();//?
            while (byteBuffer.hasRemaining()) {
                sb.append((char) byteBuffer.get());
            }
            byteBuffer.clear();
            bytesRead = channel.read(byteBuffer);
        }
        if (bytesRead == -1) {
            channel.close();
        }
        System.out.println(sb.toString());


    }

    private static void handleWrite(SelectionKey key) throws IOException {
        System.out.println("write ...");
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        SocketChannel channel = (SocketChannel) key.channel();
        channel.write(byteBuffer);
        byteBuffer.compact();
    }
}
