package pers.dyx.netty.protobuf.server;

/**
 * Netty 服务端主程序
 *
 * @author dyx
 * @date 2018年7月11日
 */
public class NettyServerApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.run();
    }

}
