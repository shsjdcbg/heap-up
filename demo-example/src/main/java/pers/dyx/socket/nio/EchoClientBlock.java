package pers.dyx.socket.nio;

import pers.dyx.socket.EchoClient;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * @author: dyx
 * @date: 2018/11/1 16:20
 * @description:
 */
public class EchoClientBlock {

    private String host = "localhost";
    private int port = 8000;
    private SocketChannel socketChannel = null;

    public EchoClientBlock() throws IOException {
        socketChannel = SocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(host,port);
        socketChannel.connect(inetSocketAddress);
        System.out.println("与服务器的连接建立成功");
    }

    private PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream outputStream = socket.getOutputStream();
        return new PrintWriter(outputStream,true);
    }

    private BufferedReader getReader(Socket socket) throws IOException{
        InputStream inputStream = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }
    public void talk(){
        try {
            BufferedReader bufferedReader = getReader(socketChannel.socket());
            PrintWriter printWriter = getWriter(socketChannel.socket());
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg=localReader.readLine())!=null){
                printWriter.println(msg);
                if("bye".equals(msg)){
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                socketChannel.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws IOException{
        new EchoClient().talk();
    }
}
