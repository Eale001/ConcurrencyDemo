package com.eale.proxy.transparent;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * 透明简单代理
 *
 * @Author HLD
 * @Date 2021/11/25 0025
 **/
public class SimpleTransProxy {


    public static void main(String[] args) throws IOException {

        int port = 10086;
        ServerSocketChannel localServer = ServerSocketChannel.open();
        localServer.bind(new InetSocketAddress(port));
        Reactor reactor = new Reactor();
        // Reactor线程
        GlobalThreadPool.REACTOR_EXECUTOR.submit(reactor::run);
        // worker单线程调试
        while (localServer.isOpen()){
            // 此处阻塞等待
            SocketChannel remoteClient = localServer.accept();
            GlobalThreadPool.WORK_EXECUTOR.submit(new Runnable() {
                @Override
                @SneakyThrows
                public void run() {
                    SocketChannel remoteServer = new ProxyHandler().proxy(remoteClient);
                    // 透明传输
                    reactor.pipe(remoteClient,remoteServer)
                            .pipe(remoteServer,remoteClient);
                }
            });
        }
    }





}
