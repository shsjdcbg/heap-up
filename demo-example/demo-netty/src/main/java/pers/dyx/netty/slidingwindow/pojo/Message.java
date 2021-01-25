package pers.dyx.netty.slidingwindow.pojo;

import com.alibaba.fastjson.JSONObject;

/**
 * @author dyx
 * @date 2019年1月16日
 */
public class Message {

    private int id;
    private String cmd;
    private String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
