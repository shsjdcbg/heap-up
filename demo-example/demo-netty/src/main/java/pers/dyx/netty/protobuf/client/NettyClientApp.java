package pers.dyx.netty.protobuf.client;

/**
 * Netty 客户端主程序
 *
 * @author dyx
 * @date 2018年7月11日
 */
public class NettyClientApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.run();
    }

}
