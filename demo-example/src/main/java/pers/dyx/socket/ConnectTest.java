package pers.dyx.socket;

import java.io.IOException;
import java.net.*;

/**
 * @author: dyx
 * @date: 2018/10/31 17:55
 * @description:
 */
public class ConnectTest {
    public static void main(String args[]) {
        String host = "localhost";
        int port = 80;
        new ConnectTest().connect(host, port);
    }

    public void connect(String host, int port) {
        SocketAddress address = new InetSocketAddress(host, port);
        Socket socket = null;
        String result = "";
        try {
            long begin = System.currentTimeMillis();
            socket = new Socket();
            socket.connect(address, 1);
            long end = System.currentTimeMillis();
            result = (end - begin) + "ms";
        } catch (BindException e) {
            result = "无法把Socket对象与指定的本地IP地址或端口绑定";
        } catch (UnknownHostException e) {
            result = "无法识别主机的名字或IP地址";
        } catch (ConnectException e) {
            result = "没有服务器进程监听指定的端口或服务器进程拒绝连接";
        } catch (SocketTimeoutException e) {
            result = "客户端等待连接超时";
        } catch (IOException e) {
            result = "失败";
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(address + ":" + result);
        System.out.println(socket.getInetAddress() + ":"
                + socket.getPort() + "," + socket.getLocalAddress() + ","
                + socket.getLocalPort() + ",");
    }
}
