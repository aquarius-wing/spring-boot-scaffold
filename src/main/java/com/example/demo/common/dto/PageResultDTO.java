package com.example.demo.common.dto;

import java.util.List;

/**
 * Created by Wing-Luo on 2017/10/16.
 */

public class PageResultDTO<T> {
    private List<T> result;
    private boolean success;
    private int code;
    private String failMsg;
    private PageInfo pageInfo;

    private PageResultDTO(List<T> result, boolean success, int code, String failMsg,
                          int page, int pageSize, int totalNum) {
        this.result = result;
        this.success = success;
        this.code = code;
        this.failMsg = failMsg;
        this.pageInfo = new PageInfo(page, pageSize, totalNum);
    }

    public class PageInfo {
        private int page;
        private int pageSize;
        private int totalNum;

        PageInfo(int page, int pageSize, int totalNum) {
            this.page = page;
            this.pageSize = pageSize;
            this.totalNum = totalNum;
        }

        public int getPage() {
            return page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getTotalNum() {
            return totalNum;
        }
    }

    public List<T> getResult() {
        return this.result;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public String getFailMsg() {
        return this.failMsg;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setResult(List<T> result) {
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

    public static <T> PageResultDTO<T> fail(String failMsg) {
        return new PageResultDTO<>(null, false, 0, failMsg, 0, 0, 0);
    }

    public static <T> PageResultDTO<T> fail(String failMsg, int code) {
        return new PageResultDTO<>(null, false, code, failMsg, 0, 0, 0);
    }

    public static <T> PageResultDTO<T> fail(String failMsg, int code, List<T> result) {
        return new PageResultDTO<>(result, false, code, failMsg, 0, 0, 0);
    }

    public static <T> PageResultDTO<T> success(List<T> result, int page, int pageSize, int totalNum) {
        return new PageResultDTO<>(result, true, 1, "", page, pageSize, totalNum);
    }

    public String toString() {
        return "ResultDTO:{"
                + "success:" + this.success + ","
                + "result:" + this.result + ","
                + "code:" + this.code + ","
                + "failMsg:" + this.failMsg + ","
                + "page:" + this.pageInfo.page + ","
                + "pageSize:" + this.pageInfo.pageSize + ","
                + "totalNum:" + this.pageInfo.totalNum + "}";
    }

}

