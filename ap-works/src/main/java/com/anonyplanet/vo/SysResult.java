package com.anonyplanet.vo;


/**
 * @author 谭应有
 */
public class SysResult {

    private Integer status;
    private String msg;
    private Object data;


    public SysResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public SysResult() {
    }

    /**
     * 自定义返回数据
     *
     * @param status 状态码
     * @param msg    描述信息
     * @param data   数据内容
     * @return
     */
    public static SysResult build(Integer status, String msg, Object data) {
        return new SysResult(status, msg, data);
    }

    /**
     * 成功返回数据
     *
     * @return
     */
    public static SysResult ok() {
        return new SysResult(200, "ok", null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
