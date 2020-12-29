package pers.dyx.socket.nio;

import java.io.*;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * @author: dyx
 * @date: 2018/11/1 14:47
 * @description:
 */
public class Handle implements Runnable {

    private SocketChannel socketChannel;

    public Handle(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        handle();
    }

    public void handle() {
        Socket socket = null;
        try {
            socket = socketChannel.socket();
            System.out.println("New connection accepted"
                    + socket.getInetAddress() + ":" + socket.getPort());
            BufferedReader bufferedReader = getReader(socket);
            PrintStream printStream = getWriter(socket);

            String msg = null;
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println(msg);
                printStream.println(echo(msg));
                if ("bye".equals(msg)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String echo(String msg) {
        return "echo" + msg;
    }

    private PrintStream getWriter(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        return new PrintStream(outputStream, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
