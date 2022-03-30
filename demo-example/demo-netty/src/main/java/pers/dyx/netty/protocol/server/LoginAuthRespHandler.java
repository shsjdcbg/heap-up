package pers.dyx.netty.protocol.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.dyx.netty.protocol.MessageType;
import pers.dyx.netty.protocol.struct.Header;
import pers.dyx.netty.protocol.struct.NettyMessage;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 握手和安全认证处理器
 *
 * @author dyx
 */
public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginAuthRespHandler.class);
    private final Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();
    private final String[] whiteList = {"127.0.0.1"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        // 如果是握手请求消息，处理，其它消息透传
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            logger.info("收到握手消息：" + message);
            NettyMessage loginResp = null;
            // 重复登陆，拒绝
            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildResponse((byte) -1);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel()
                        .remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isok = false;
                for (String wip : whiteList) {
                    if (wip.equals(ip)) {
                        isok = true;
                        break;
                    }
                }
                loginResp = isok ? buildResponse((byte) 0) : buildResponse((byte) -1);
                if (isok) {
                    nodeCheck.put(nodeIndex, true);
                }
            }
            logger.info("回应握手消息：" + loginResp);
            ctx.writeAndFlush(loginResp);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 删除缓存
        nodeCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

    private NettyMessage buildResponse(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }
}
