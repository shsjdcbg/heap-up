package pers.dyx.netty.protocol.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.dyx.netty.protocol.MessageType;
import pers.dyx.netty.protocol.server.LoginAuthRespHandler;
import pers.dyx.netty.protocol.struct.Header;
import pers.dyx.netty.protocol.struct.NettyMessage;

/**
 * 握手和安全认证处理器
 *
 * @author dyx
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginAuthRespHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyMessage message = buildLoginReq();
        logger.info("发送握手请求：" + message);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        // 如果是握手应答消息，需要判断是否认证成功
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            logger.info("握手请求结果返回：" + message);
            byte loginResult = (byte) message.getBody();
            if (loginResult != (byte) 0) {
                // 握手失败，关闭连接
                ctx.close();
            } else {
                // 将消息至下一个处理器
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }
}
