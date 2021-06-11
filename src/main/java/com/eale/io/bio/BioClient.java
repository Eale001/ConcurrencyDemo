package com.eale.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author Admin
 * @Date 2021/2/25
 * @Description //Bio 客户端
 * @Version 1.0
 **/
public class BioClient {

    private final static String ADDRESS = "127.0.0.1";

    private final static Integer PORT = 33034;


    public static void main(String[] args) {

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(ADDRESS,PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("接收到客户端的请求数据...");
            out.println("你好服务器我是客户端！！！");
            String s = in.readLine();
            System.out.println("client :"+ s);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
