package com.eale.io.netty.handler;

import com.eale.io.netty.client.Client1;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author HLD
 * @Date 2021/6/11 0011
 * @Description //TODO
 * @Version 1.0
 **/
public class ClientHandler1 extends CustomHeartbeatHandler {

    private Client1 client;

    public ClientHandler1(Client1 client) {
        super("client");
        this.client = client;
    }

    @Override
    public void handleData(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        byte[] data = new byte[byteBuf.readableBytes() - 5];
        byteBuf.skipBytes(5);
        byteBuf.readBytes(data);
        String content = new String(data);
        System.out.println(name + " get content: " + content);
    }

    @Override
    protected void handleAllIdle(ChannelHandlerContext ctx) {
        super.handleAllIdle(ctx);
        sendPingMsg(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        client.doConnect();
    }
}
