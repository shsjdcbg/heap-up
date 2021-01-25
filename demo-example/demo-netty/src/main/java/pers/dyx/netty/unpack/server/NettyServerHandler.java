package pers.dyx.netty.unpack.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Netty服务端业务逻辑处理  用于测试粘包、拆包
 *
 * @author dyx
 * @date 2017年9月20日
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String body = (String) msg;
        System.out.println("接受的数据是: " + body + ";条数是: " + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
