package pers.dyx.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @author: dyx
 * @date: 2018/10/31 17:43
 * @description:
 */
public class PortScanner {
    public void scan(String host) {
        Socket socket = null;
        for (int port = 1; port < 1024; port++) {
            try {
                socket = new Socket(host, port);
                System.out.println("There is a server on port " + port);
            } catch (IOException e) {
                System.out.println("Can't connect to port " + port);
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String args[]){
        String host = "localhost";
        new PortScanner().scan(host);
    }
}
