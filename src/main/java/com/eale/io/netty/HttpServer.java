package com.eale.io.netty;

import com.eale.io.netty.handler.HttpHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

/**
 * @Author HLD
 * @Date 2021/6/9 0009
 * @Description //HttpServer
 * @Version 1.0
 **/
public class HttpServer {

    private final int port;


    public HttpServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1){
            System.err.println("Usage" + HttpServer.class.getSimpleName() + "<port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new HttpServer(port).start();

    }

    private void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("initChannel ch : "+ socketChannel);
                        socketChannel.pipeline()
                                .addLast("decoder",new HttpRequestDecoder())
                                .addLast("encoder",new HttpResponseDecoder())
                                .addLast("aggregator",new HttpObjectAggregator(512*1024))
                                .addLast("handler",new HttpHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG,128) // 确定排队的连接数
                .childOption(ChannelOption.SO_KEEPALIVE,Boolean.TRUE);
        bootstrap.bind(port).sync();
    }


}
