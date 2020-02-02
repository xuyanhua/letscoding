package com.yanhua.javabase.net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        //1.创建socket，并绑定到特定的端口
        ServerSocket serverSocket = new ServerSocket(10086);
        while (true) {
            //2.准备接收客户端连接
            Socket client = serverSocket.accept();
            new Thread(() -> {
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    //3.读取客户端的数据
                    inputStream = client.getInputStream();
                    byte[] buf = new byte[1024];
                    int read = inputStream.read(buf);
                    System.out.println("打印来自客户端的数据：" + new String(buf, 0, read));
                    //4.向客户端写入数据
                    outputStream = client.getOutputStream();
                    outputStream.write("hello client".getBytes());
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        outputStream.close();
                        inputStream.close();
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}
