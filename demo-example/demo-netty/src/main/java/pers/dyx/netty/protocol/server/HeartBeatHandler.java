package pers.dyx.netty.protocol.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.dyx.netty.protocol.MessageType;
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
     * 发送次数
     */
    private int count = 1;

    /**
     * 超时处理
     * 如果5秒没有接受客户端的心跳，就触发;
     * 如果超过两次，则直接关闭;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            //如果读通道处于空闲状态，说明没有接收到心跳命令
            if (IdleState.READER_IDLE.equals(event.state())) {
                logger.info("已经5秒没有接收到客户端的信息了");
                if (idleCount > 1) {
                    logger.info("关闭这个不活跃的channel");
                    ctx.channel().close();
                }
                idleCount++;
            }
        } else {
            super.userEventTriggered(ctx, obj);
        }
    }

    /**
     * 业务逻辑处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            logger.info("第" + count + "次" + ",服务端接受的心跳消息：" + message);
        } else {
            ctx.fireChannelRead(msg);
        }
        count++;
    }
}
