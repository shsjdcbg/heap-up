package pers.dyx.netty.protocol;

/**
 * 消息类型
 *
 * @author dyx
 */
public enum MessageType {

    /**
     * 0：业务请求消息
     */
    SERVICE_REQ((byte) 0),
    /**
     * 1：业务响应消息
     */
    SERVICE_RESP((byte) 1),

    /**
     * 2：既是请求又是响应消息
     */
    ONE_WAY((byte) 2),

    /**
     * 3：握手请求消息
     */
    LOGIN_REQ((byte) 3),

    /**
     * 4：握手应答消息
     */
    LOGIN_RESP((byte) 4),

    /**
     * 5：心跳请求消息
     */
    HEARTBEAT_REQ((byte) 5),

    /**
     * 6：心跳应答消息
     */
    HEARTBEAT_RESP((byte) 6);

    private byte value;

    private MessageType(byte value) {
        this.value = value;
    }

    public byte value() {
        return this.value;
    }
}
