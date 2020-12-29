package pers.dyx.utils;


import com.alibaba.fastjson.JSONObject;

public class JsonResult {
    private String code;
    private String message;
    private Object data;

    public JsonResult() {
        this.setCode(ResultCode.SUCCESS);
        this.setMessage(ResultCode.SUCCESS.msg());
    }

    public JsonResult(ResultCode code) {
        this.setCode(code);
        this.setMessage(code.msg());
    }

    public JsonResult(ResultCode code, String data) {
        this.setCode(code);
        this.setMessage(code.msg());
        this.setData(data);
    }

    public JsonResult(ResultCode code, Object data) {
        this.setCode(code);
        this.setMessage(code.msg());
        this.setData(data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code.val();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toJsonString() {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("message", message);
        json.put("data", data);
        return json.toString();
    }
}
