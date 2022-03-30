package pers.dyx.netty.protocol.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.dyx.netty.protocol.NettyConstant;
import pers.dyx.netty.protocol.codec.NettyMessageDecoder;
import pers.dyx.netty.protocol.codec.NettyMessageEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dyx
 */
public class NettyClient {

    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    // 通过nio方式来接收连接和处理连接
    private static final EventLoopGroup GROUP = new NioEventLoopGroup();

    public void connect(int port, String host) throws Exception {
        try {
            Bootstrap b = new Bootstrap();
            b.group(GROUP).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
                            ch.pipeline().addLast(
                                    new NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            ch.pipeline().addLast(new LoginAuthReqHandler());
                            ch.pipeline().addLast(new HeartBeatHandler());
                        }
                    });
            // 发起异步连接操作
            ChannelFuture future = b.connect(
                    new InetSocketAddress(host, port),
                    new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();
            // 当对应的channel关闭的时候，就会返回对应的channel。
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("客户端连接失败：" + e.getMessage());
        } finally {
            // 所有资源释放完成之后，清空资源，再次发起重连操作
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        try {
                            // 发起重连操作
                            logger.info("发起重连操作");
                            connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyClient().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
    }
}
