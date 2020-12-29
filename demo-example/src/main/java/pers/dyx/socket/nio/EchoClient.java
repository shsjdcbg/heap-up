package pers.dyx.socket.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author: dyx
 * @date: 2018/11/1 16:30
 * @description:
 */
public class EchoClient {
    private String host = "localhost";
    private int port = 8000;
    private SocketChannel socketChannel = null;
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer receiveBuffer = ByteBuffer.allocateDirect(1024);
    private Selector selector;
    private Charset charset = Charset.forName("GBK");

    public EchoClient() {
        try {
            socketChannel = SocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(host,port);
            socketChannel.connect(inetSocketAddress);
            socketChannel.configureBlocking(false);
            System.out.println("与服务器的连接建立成功");
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){

    }

    public void receiveFromUser(){
        try {
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg=localReader.readLine())!=null){
                synchronized (sendBuffer){
                    sendBuffer.put(encode("msg"+"\r\n"));
                }
                if("bye".equals(msg)){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void talk(){
        try {
            socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }


    private String decode(ByteBuffer buffer) {
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }

    private ByteBuffer encode(String str) {
        return charset.encode(str);
    }
}
