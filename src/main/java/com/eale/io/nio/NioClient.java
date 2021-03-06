package com.eale.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * @Date 2021/2/25
 * @Description // nio 客户端
 * @Version 1.0
 **/
public class NioClient {

    private static final String ADDRESS = "127.0.0.1";
    private static final Integer PORT = 33035;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer resultBuffer = ByteBuffer.allocate(1024);
        SocketChannel channel = null;
        try {
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress(ADDRESS,PORT));
            if (channel.finishConnect()){
                int i = 0;
                while (true){
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm "+i+++"-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while (buffer.hasRemaining()){
                        System.out.println("1212121212"+buffer);
                        channel.write(buffer);
                    }
                    buffer.clear();
                    Thread.sleep(1000);
                    int relength = channel.read(buffer);
                    byte[] bytes = new byte[relength];
                    buffer.flip();
                    buffer.get(bytes);
                    String result = new String(bytes).trim();
                    buffer.clear();
                    System.out.println(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                if (channel != null){
                    channel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
