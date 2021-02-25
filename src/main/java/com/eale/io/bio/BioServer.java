package com.eale.io.bio;

import com.eale.io.bio.handler.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Admin
 * @Date 2021/2/25
 * @Description //bio 服务端
 * @Version 1.0
 **/
public class BioServer {

    private final static Integer PORT = 33033;

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            System.out.println(" 服务启动成功。。。");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }


        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
