package com.eale.io.bio.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author Admin
 * @Date 2021/2/25
 * @Description //
 * @Version 1.0
 **/
public class ServerHandler implements Runnable {


    private Socket socket;

    public ServerHandler(Socket socket) {

        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("接收到服务端的请求数据...");
            out.println("你好客户端我是服务器！！！");
            String s = in.readLine();
            System.out.println("server :"+ s);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
