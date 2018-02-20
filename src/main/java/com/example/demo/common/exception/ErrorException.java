package com.example.demo.common.exception;

public class ErrorException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ErrorException(String message, Integer code) {
        super(message);
        this.code = code;
    }public ErrorException(String message) {
        super(message);
        this.code = 0;
    }
}
