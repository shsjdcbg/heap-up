package pers.dyx.netty.protocol.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.dyx.netty.protocol.MessageType;
import pers.dyx.netty.protocol.struct.Header;
import pers.dyx.netty.protocol.struct.NettyMessage;

/**
 * @author dyx
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HeartBeatHandler.class);

    /**
     * 空闲次数
     */
    private int idleCount = 1;

    /**
     * 心跳请求处理
     * 每4秒发送一次心跳请求;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            // 如果写通道处于空闲状态，就发送心跳消息
            if (IdleState.WRITER_IDLE.equals(event.state())) {
//                // 设置发送次数，用于测试
//                if (idleCount <= 2) {
//                    idleCount++;
                    NettyMessage heatBeat = buildHeatBeat();
                    logger.info("发送心跳消息：" + heatBeat);
                    ctx.channel().writeAndFlush(heatBeat);
//                } else {
//                    logger.error("不再发送心跳请求了!");
//                }
            }
        }
    }

    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_REQ.value());
        message.setHeader(header);
        return message;
    }
}
