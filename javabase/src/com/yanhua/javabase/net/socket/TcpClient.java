package com.yanhua.javabase.net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        //1.创建socket连接，指定ip和端口
        Socket client = new Socket("127.0.0.1",10086);
        OutputStream outputStream = client.getOutputStream();
        outputStream.write("hello server".getBytes());
        outputStream.flush();
        InputStream inputStream = client.getInputStream();
        byte[] buf = new byte[1024];
        int read = inputStream.read(buf);
        System.out.println("打印来自服务端的数据：" + new String(buf, 0, read));
        outputStream.close();
        inputStream.close();
        client.close();
    }
}
