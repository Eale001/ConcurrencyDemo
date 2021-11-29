package com.eale.proxy.transparent;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @Author HLD
 * @Date 2021/11/25 0025
 **/
public class GlobalThreadPool {


    public static EventLoopGroup REACTOR_EXECUTOR = new NioEventLoopGroup();

    public static EventLoopGroup WORK_EXECUTOR = new NioEventLoopGroup();

    public static EventLoopGroup PIPE_EXECUTOR = new NioEventLoopGroup();



}
