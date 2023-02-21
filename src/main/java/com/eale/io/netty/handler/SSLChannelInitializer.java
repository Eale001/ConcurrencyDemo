package com.eale.io.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLEngine;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * @Author HLD
 * @Date 2021/6/9 0009
 **/
public class SSLChannelInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext sslContext;

    public SSLChannelInitializer() {
        String keyStoreFilePath = "/securesocket.jks";
        String keyStorePassword = "Password@123";

        try {
            KeyStore jks = KeyStore.getInstance("JKS");
            jks.load(new FileInputStream(keyStoreFilePath),keyStorePassword.toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(jks,keyStorePassword.toCharArray());
            sslContext = SslContextBuilder.forServer(keyManagerFactory).build();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        SSLEngine sslEngine = sslContext.newEngine(socketChannel.alloc());
        pipeline.addLast(new SslHandler(sslEngine))
                .addLast("decoder",new HttpRequestDecoder())
                .addLast("encoder",new HttpResponseEncoder())
                .addLast("aggregator",new HttpObjectAggregator(512*1024))
                .addLast("handler",new HttpHandler());

    }
}
