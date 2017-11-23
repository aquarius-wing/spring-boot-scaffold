package com.example.demo.common.dto;

/**
 * Created by Wing-Luo on 2017/10/16.
 */

public class ResultDTO<T> {
    private T result;
    private boolean success;
    private int code;
    private String failMsg;

    private ResultDTO(T result, boolean success, int code, String failMsg) {
        this.result = result;
        this.success = success;
        this.code = code;
        this.failMsg = failMsg;
    }

    public T getResult() {
        return this.result;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public String getFailMsg() {
        return this.failMsg;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        if (success) {
            this.code = 1;
        }
    }

    public void setFailMsg(String failMsg) {
        this.failMsg = failMsg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static <T> ResultDTO<T> fail(String failMsg) {
        return new ResultDTO<>(null, false, 0, failMsg);
    }

    public static <T> ResultDTO<T> fail(String failMsg, int code) {
        return new ResultDTO<>(null, false, code, failMsg);
    }

    public static <T> ResultDTO<T> fail(String failMsg, int code, T result) {
        return new ResultDTO<>(result, false, code, failMsg);
    }

    public static <T> ResultDTO<T> success(T result) {
        return new ResultDTO<>(result, true, 1, "");
    }

    public String toString() {
        return "ResultDTO:{" + "success:" + this.success + "," + "result:" + this.result + "," + "code:" + this.code + "," + "failMsg:" + this.failMsg + "}";
    }

}

