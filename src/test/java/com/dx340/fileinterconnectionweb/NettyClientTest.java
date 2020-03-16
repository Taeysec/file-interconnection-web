package com.dx340.fileinterconnectionweb;

import io.netty.util.CharsetUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NettyClientTest {
    private static byte[] buff = new byte[1024 * 2];

    public static void main(String[] args) throws Exception {
        Socket client = new Socket("127.0.0.1", 9090);
        OutputStream outputStream = client.getOutputStream();
        InputStream inputStream = client.getInputStream();
        outputStream.write("E:\\A_PicMerge\\branches\\base\\doc\\dst111.jpg".getBytes(CharsetUtil.UTF_8));
        System.out.println(readMessage(inputStream, buff));
        client.close();
    }

    private static String readMessage(InputStream inputStream, byte[] buff) throws Exception {
        int read = inputStream.read(buff);
        return new String(buff, 0, read, StandardCharsets.UTF_8);
    }

}
