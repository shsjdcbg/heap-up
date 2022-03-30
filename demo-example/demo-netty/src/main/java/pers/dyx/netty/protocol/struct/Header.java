package pers.dyx.netty.protocol.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息头
 *
 * @author dyx
 */
public class Header {

    /**
     * 校验码
     */
    private int crcCode;

    /**
     * 消息的长度
     */
    private int length;

    /**
     * 会话ID
     */
    private long sessionId;

    /**
     * 消息类型
     * 0：业务请求消息
     * 1：业务响应消息
     * 2：既是请求又是响应消息
     * 3：握手请求消息
     * 4：握手应答消息
     * 5：心跳请求消息
     * 6：心跳应答消息
     */
    private byte type;

    /**
     * 消息优先级：0~255
     */
    private byte priority;

    /**
     * 可选字段，用于扩展消息头
     */
    private Map<String, Object> attachment = new HashMap<>();

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionId=" + sessionId +
                ", type=" + type +
                ", priority=" + priority +
                ", attachment=" + attachment +
                '}';
    }
}
