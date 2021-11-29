package com.eale.proxy.transparent;

import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author HLD
 * @Date 2021/11/25 0025
 **/
@Data
public class ProxyHandler {

    private String method;
    private String host;
    private Integer port;
    private SocketChannel remoteServer;
    private SocketChannel remoteClient;

    /**
     * 原始信息
     */
    private List<ByteBuffer> buffers = new ArrayList<>();
    private StringBuilder stringBuilder = new StringBuilder();


    /**
     * 连接到远程
     * @param remoteClient
     * @return
     */
    public SocketChannel proxy(SocketChannel remoteClient) throws IOException {
        this.remoteClient = remoteClient;
        connect();
        return this.remoteServer;
    }

    private void connect() throws IOException {
        // 解析METHOD host 和 port
        beforeConnected();

        // 连接 remoteServer
        createRemoteServer();

        // connect 请求回应
        afterConnected();
    }


    private void beforeConnected() throws IOException {
        // 读取header
        readAllHeader();

        // 解析host和port
        parseRemoteHostAndPort();
    }

    private void readAllHeader() throws IOException {
        while (true){
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            int read = remoteClient.read(byteBuffer);
            byteBuffer.flip();
            appendClientBuffer(byteBuffer);
            if (read < byteBuffer.capacity()){
                break;
            }
        }
    }

    private void appendClientBuffer(ByteBuffer byteBuffer) {
        buffers.add(byteBuffer);
        stringBuilder.append(new String(byteBuffer.array()),byteBuffer.position(),byteBuffer.limit());

    }

    /**
     * 解析出 host port
     */
    private void parseRemoteHostAndPort() {
        method = parseRequestMethod(stringBuilder.toString());
        port = 80;
        if ("CONNECT".equalsIgnoreCase(method)){
            port = 443;
        }
        this.host = parseHost(stringBuilder.toString());
        URI remoteServerUri = URI.create(host);
        host = remoteServerUri.getHost();

        if (remoteServerUri.getPort() > 0) {
            port = remoteServerUri.getPort();
        }
    }


    private String parseRequestMethod(String rowContent) {
        return rowContent.split("\r\n")[0].split(" ")[0];
    }

    private String parseHost(String rowContent) {
        String[] headers = rowContent.split("\r\n");
        String host = "host:";
        for (String header : headers) {
            if (header.length() > host.length()) {
                String key = header.substring(0, host.length());
                String value = header.substring(host.length()).trim();
                if (host.equalsIgnoreCase(key)) {
                    if (!value.startsWith("http://") && !value.startsWith("https://")) {
                        value = "http://" + value;
                    }
                    return value;
                }
            }
        }
        return "";
    }

    /**
     * 创建远程连接
     */
    private void createRemoteServer() throws IOException {
        remoteServer = SocketChannel.open(new InetSocketAddress(host,port));
    }

    /**
     * 建立连接后进行 预处理
     */
    private void afterConnected() throws IOException {
        if ("CONNECT".equalsIgnoreCase(method)){
            // CONNECT 默认为443端口 根据host再解析
            remoteClient.write(ByteBuffer.wrap("HTTP/1.0 200 Connection Established\\r\\nProxy-agent: nginx\\r\\n\\r\\n".getBytes()));
        }else {
            writeThrough();
        }
    }

    private void writeThrough() {
        buffers.forEach(buffer ->{
            try {
                remoteServer.write(buffer);
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }



}
