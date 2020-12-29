package pers.dyx.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: dyx
 * @date: 2018/11/1 14:55
 * @description: 非阻塞
 */
public class EchoServer {
    private int port = 8000;
    private ServerSocketChannel serverSocketChannel = null;
    private ExecutorService executorService;
    private Selector selector = Selector.open();
    private static final int POOL_MULTIPLE = 4;
    private Charset charset = Charset.forName("GBK");

    public EchoServer() throws IOException {
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * POOL_MULTIPLE);
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().setReuseAddress(true);
        //非阻塞模式
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("服务器启动成功");
    }

    public void service() {
        SelectionKey key = null;
        try {
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (selector.select() > 0) {
                Set readyKeys = selector.selectedKeys();
                Iterator it = readyKeys.iterator();
                while (it.hasNext()) {
                    key = (SelectionKey) it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        acceptable(key);
                    }
                    if (key.isReadable()) {
                        receive(key);
                    }
                    if (key.isWritable()) {
                        send(key);
                    }
                }
            }
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (key != null) {
                key.cancel();
                try {
                    key.channel().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void acceptable(SelectionKey key) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        try {
            SocketChannel socketChannel = (SocketChannel) serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receive(SelectionKey key) {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer readBuff = ByteBuffer.allocate(32);
        try {
            socketChannel.read(readBuff);
            readBuff.flip();
            buffer.limit(buffer.capacity());
            buffer.put(readBuff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send(SelectionKey key) {
        try {
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            SocketChannel socketChannel = (SocketChannel) key.channel();
            buffer.flip();
            String data = decode(buffer);
            if (data.indexOf("\r\n") == 1) {
                return;
            }
            String outputData = data.substring(0, data.indexOf("\n") + 1);
            System.out.println(outputData);
            ByteBuffer outputBuffer = encode("echo" + outputData);
            while (outputBuffer.hasRemaining()) {
                socketChannel.write(outputBuffer);
            }
            ByteBuffer temp = encode(outputData);
            buffer.position(temp.limit());
            buffer.compact();
            if("bye\r\n".equals(outputData)){
                key.cancel();
                socketChannel.close();
                System.out.println("关闭与客户的连接");
            }
        } catch (IOException e) {
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

    public static void main(String args[]) {
        try {
            new EchoServerBlock().service();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
