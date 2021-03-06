package com.eale.io.bio;

import com.eale.io.bio.handler.HandlerExecutorPool;
import com.eale.io.bio.handler.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Admin
 * @Date 2021/2/25
 * @Description //BioServer1
 * @Version 1.0
 **/
public class BioServer1 {

    private static final Integer PORT = 33034;

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            HandlerExecutorPool executorPool = new HandlerExecutorPool(50,20);
            System.out.println("服务器开启.....");
            while (true){
                Socket accept = serverSocket.accept();
                executorPool.execute(new ServerHandler(accept));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
