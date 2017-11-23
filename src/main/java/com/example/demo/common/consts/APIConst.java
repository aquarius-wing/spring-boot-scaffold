package com.example.demo.common.consts;

/**
 * Created by Wing-Luo on 2017/8/2.
 */
public class APIConst {

    private APIConst() {
    }

    public static final String API_ACCOUNT_ID = "accountId";
    public static final String API_PAGE = "page";
    public static final String API_PAGE_SIZE = "pageSize";
    public static final String API_START_DATE = "startDate";
    public static final String API_END_DATE = "endDate";
    public static final String API_SEARCH_KEY = "searchKey";

    public static class ErrorCode {
        private ErrorCode() {
        }

        public static final int ILLEGAL_ACCOUNT_ID = 9998;
        public static final int NOT_LOGIN = 9999;
    }

}
