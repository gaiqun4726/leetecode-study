package com.gaiqun.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author gaiqun
 * @date 2021/2/2
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while(true) {
            Socket socket = serverSocket.accept();
            byte[] bytes = new byte[1024];
            socket.getInputStream().read(bytes);
            System.out.println(new String(bytes));
            byte[] response = "hello".getBytes();
            socket.getOutputStream().write(response);
            socket.close();
        }
    }
}
