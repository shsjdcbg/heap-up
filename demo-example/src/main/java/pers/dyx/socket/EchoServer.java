package pers.dyx.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: dyx
 * @date: 2018/10/31 17:17
 * @description:
 */
public class EchoServer {
    private int port = 8000;
    private ServerSocket serverSocket;

    public EchoServer() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("服务器启动成功");
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

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("New connection accepted"
                        + socket.getInetAddress() + ":" + socket.getPort());
                BufferedReader bufferedReader = getReader(socket);
                PrintStream printStream = getWriter(socket);

                String msg = null;
                while ((msg = bufferedReader.readLine())!=null){
                    System.out.println(msg);
                    printStream.println(echo(msg));
                    if("bye".equals(msg)){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(socket!=null) {
                        socket.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) throws IOException{
        new EchoServer().service();
    }
}
