package pers.dyx.netty.hello.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Netty客户端 过滤器
 *
 * @author dyx
 * @date 2017-8-31
 */
public class NettyClientFilter extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline ph = ch.pipeline();
        // 解码和编码，应和服务端一致
        ph.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        ph.addLast("decoder", new StringDecoder());
        ph.addLast("encoder", new StringEncoder());
        // 客户端的逻辑
        ph.addLast(new NettyClientHandler());
    }
}
