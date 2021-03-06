package com.eale.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author Admin
 * @Date 2021/2/25
 * @Description //
 * @Version 1.0
 **/
public class NioServer implements Runnable{

    public static void main(String[] args) {
        new Thread(new NioServer(33035)).start();
    }

    // 多路复用器
    private Selector selector;
    // 读写缓冲区
    private ByteBuffer readBuffer = ByteBuffer.allocate(10240);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(10240);
    private String agreement = "idea-0329";
    public NioServer (int port){
        try {
            // 打开多路复用器
            selector = Selector.open();
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.bind(new InetSocketAddress(port));
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器开启......");
            //  将String 附件写入 write 中
            writeBuffer.put(agreement.getBytes());
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true){
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()){
                        //
                        accept(key);
                    }else if (key.isValid() && key.isWritable()){
                        write(key);
                    }else if (key.isValid() && key.isReadable()){
                        read(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey key) {
        try {
            // 获取当前服务器通道
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            // 切换客户通道
            SocketChannel accept = channel.accept();
            // 设置非阻塞
            accept.configureBlocking(false);
            // 注册一个读事件, 留意该客户端是否有数据进来
            accept.register(selector,SelectionKey.OP_WRITE,writeBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        writeBuffer.clear();
        writeBuffer.put("ok , replay ... ".getBytes());
        writeBuffer.flip();
        try {
            channel.write(writeBuffer);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            key.cancel();
        }
    }

    private void read(SelectionKey key) {
        readBuffer.clear();
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            int read = channel.read(readBuffer);
            if (read == -1){
                key.channel().close();
                key.cancel();
                return;
            }
            readBuffer.flip();
            byte[] bytes = new byte[read];
            readBuffer.get(bytes);
            String result = new String(bytes).trim();
            System.out.println("收到的信息:   "+result);
            write(key);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                //当客户端出现连接异常的时候，关闭当前客户端通道，取消缓存key
                channel.close();
                key.cancel();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
