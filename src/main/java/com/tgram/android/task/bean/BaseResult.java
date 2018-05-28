package com.tgram.android.task.bean;

/**
 * 项目名称：TaskCheck
 * 类描述：所有返回结果的格式
 * 创建人：mzgkq
 * 创建时间：18/3/9
 */
public class BaseResult<T> {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
