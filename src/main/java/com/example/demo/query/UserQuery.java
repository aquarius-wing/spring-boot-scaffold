
 package com.example.demo.query;

import com.example.demo.common.dto.LeftJoinDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wing.
 */
public class UserQuery {

    private Integer id;

    private String uname;

    private String password;

    private Integer status;

    // 【排除状态查询】
    private Integer statusExclude;

    // 【in查询使用，形式：1,2,3,4,5】
    private String ids;

    // 【排序字符串】
    private String sortStr;

    // 【like】
    private Map<String, Integer> selectType = new HashMap<>();

    // 【分页】
    private Integer offset;
    private Integer limit;

    // 【左连接集合】
    private LeftJoinDTO leftJoinDTO;

    public String getIds() {
        return ids;
    }

    public UserQuery withIds(String ids) {
        this.ids = ids;
        return this;
    }

    public String getSortStr() {
        return sortStr;
    }

    public UserQuery withSortStr(String sortStr) {
        this.sortStr = sortStr;
        return this;
    }

    public Map<String, Integer> getSelectType() {
        return selectType;
    }

    public UserQuery withSelectType(Map<String, Integer> selectType) {
        this.selectType = selectType;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public UserQuery withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public UserQuery withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public UserQuery withPaging(Integer page, Integer pageSize) {
        this.offset = (page - 1) * pageSize;
        this.limit = pageSize;
        return this;
    }

    public LeftJoinDTO getLeftJoinDTO(){
        return leftJoinDTO;
    }

    public UserQuery withLeftJoinDTO(LeftJoinDTO leftJoinDTO){
        this.leftJoinDTO = leftJoinDTO;
        return this;
    }

    public UserQuery withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return this.id;
    }

    public UserQuery withUname(String uname) {
        this.uname = uname;
        return this;
    }

    public String getUname() {
        return this.uname;
    }

    public UserQuery withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public UserQuery withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getStatus() {
        return this.status;
    }

    public UserQuery withStatusExclude(Integer statusExclude) {
        this.statusExclude = statusExclude;
        return this;
    }

    public Integer getStatusExclude() {
        return this.statusExclude;
    }



}

