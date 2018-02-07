
 package com.example.demo.query;

import com.example.demo.common.dto.LeftJoinDTO;
import com.example.demo.dao.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wing.
 */
public class UserRoleQuery {

    private Integer id;

    private Integer uid;

    private Integer role;

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

    public UserRoleQuery withIds(String ids) {
        this.ids = ids;
        return this;
    }

    public String getSortStr() {
        return sortStr;
    }

    public UserRoleQuery withSortStr(String sortStr) {
        this.sortStr = sortStr;
        return this;
    }

    public Map<String, Integer> getSelectType() {
        return selectType;
    }

    public UserRoleQuery withSelectType(Map<String, Integer> selectType) {
        this.selectType = selectType;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public UserRoleQuery withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public UserRoleQuery withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public UserRoleQuery withPaging(Integer page, Integer pageSize) {
        this.offset = (page - 1) * pageSize;
        this.limit = pageSize;
        return this;
    }

    public LeftJoinDTO getLeftJoinDTO(){
        return leftJoinDTO;
    }

    public UserRoleQuery withLeftJoinDTO(LeftJoinDTO leftJoinDTO){
        this.leftJoinDTO = leftJoinDTO;
        return this;
    }

    public UserRoleQuery withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return this.id;
    }

    public UserRoleQuery withUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public Integer getUid() {
        return this.uid;
    }

    public UserRoleQuery withRole(Integer role) {
        this.role = role;
        return this;
    }

    public Integer getRole() {
        return this.role;
    }


    private User user;

    public void setUser(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}

