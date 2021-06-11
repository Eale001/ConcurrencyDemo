package com.eale.io.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Author HLD
 * @Date 2021/6/11 0011
 * @Description //CustomHeartbeatHandler 心跳
 * @Version 1.0
 **/
public abstract class CustomHeartbeatHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static final byte PING_MSG = 1;

    private static final byte PONG_MSG = 2;

    public static final byte CUSTOM_MSG = 3;

    protected String name;

    private int heartbeatCount = 0;

    public CustomHeartbeatHandler(String name) {
        this.name = name;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        if (byteBuf.getByte(4) == PING_MSG){
            sendPongMsg(channelHandlerContext);
        }else if (byteBuf.getByte(4) == PONG_MSG){
            System.out.println(name + " get pong msg from "+ channelHandlerContext.channel().remoteAddress());
        }else {
            //
            handleData(channelHandlerContext,byteBuf);
        }
    }

    public abstract void handleData(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf);

    protected void sendPingMsg(ChannelHandlerContext channelHandlerContext){
        ByteBuf byteBuf = channelHandlerContext.alloc().buffer(5);
        byteBuf.writeInt(5);
        byteBuf.writeByte(PING_MSG);
        channelHandlerContext.channel().writeAndFlush(byteBuf);
        heartbeatCount++;
        System.out.println(name + " send ping msg to " + channelHandlerContext.channel().remoteAddress() + ", count : "+ heartbeatCount);
    }

    private void sendPongMsg(ChannelHandlerContext channelHandlerContext) {
        ByteBuf byteBuf = channelHandlerContext.alloc().buffer(5);
        byteBuf.writeInt(5);
        byteBuf.writeByte(PONG_MSG);
        channelHandlerContext.channel().writeAndFlush(byteBuf);
        heartbeatCount++;
        System.out.println(name + " send pong msg to " + channelHandlerContext.channel().remoteAddress() + ", count : "+ heartbeatCount);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // IdleStateHandler 所产生的 IdleStateEvent 的处理逻辑.
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case READER_IDLE:
                    handleReaderIdle(ctx);
                    break;
                case WRITER_IDLE:
                    handleWriterIdle(ctx);
                    break;
                case ALL_IDLE:
                    handleAllIdle(ctx);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("---" + ctx.channel().remoteAddress() + " is active---");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("---" + ctx.channel().remoteAddress() + " is inactive---");
    }


    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        System.err.println("---READER_IDLE---");
    }

    protected void handleWriterIdle(ChannelHandlerContext ctx) {
        System.err.println("---WRITER_IDLE---");
    }

    protected void handleAllIdle(ChannelHandlerContext ctx) {
        System.err.println("---ALL_IDLE---");
    }
}
