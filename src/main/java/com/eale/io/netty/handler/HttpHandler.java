package com.eale.io.netty.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author HLD
 * @Date 2021/6/9 0009
 * @Description // HttpHandler 处理器
 * @Version 1.0
 **/
@Slf4j
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private AsciiString contentType = HttpHeaderValues.TEXT_PLAIN;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        System.out.println("class: "+ fullHttpRequest.getClass().getSimpleName());
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.wrappedBuffer("test".getBytes()));
        HttpHeaders headers = response.headers();
        headers.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
        headers.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        headers.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        System.out.println(response.toString());
        ctx.writeAndFlush(response);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("message : {}",msg.toString());
        if (msg instanceof HttpRequest) {

            // 请求，解码器将请求转换成HttpRequest对象
            HttpRequest request = (HttpRequest) msg;

            // 获取请求参数
            QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
            String name = "netty";
            if(queryStringDecoder.parameters().get("name") != null) {
                name = queryStringDecoder.parameters().get("name").get(0);
            }

            // 响应HTML
            String responseHtml = "<html><body>Hello, " + name + "</body></html>";
            byte[] responseBytes = responseHtml.getBytes("UTF-8");
            int contentLength = responseBytes.length;

            // 构造FullHttpResponse对象，FullHttpResponse包含message body
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(responseBytes));
            response.headers().set("Content-Type", "text/html; charset=utf-8");
            response.headers().set("Content-Length", Integer.toString(contentLength));

            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exception caught");
        if (cause != null) cause.printStackTrace();
        if (ctx != null) ctx.close();
    }
}
