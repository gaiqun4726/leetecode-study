package com.gaiqun.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author gaiqun
 * @date 2021/2/2
 */
public class BioClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 8080));
        socket.getOutputStream().write("client connected".getBytes());
        byte[] response = new byte[1024];
        socket.getInputStream().read(response);
        System.out.println(new String(response));
        socket.close();
    }

}
