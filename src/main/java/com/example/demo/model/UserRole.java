
 package com.example.demo.model;

import java.util.Date;

/**
 * Created by rapid-generator.
 */
public class UserRole {

    /**
     * 描述: id
     * 字段: id  int(10)
     */
    private Integer id;

    /**
     * 描述: uid
     * 字段: uid  int(10)
     */
    private Integer uid;

    /**
     * 描述: 0是普通用户,1是高级会员,2是终极会员
     * 字段: role  int(10)
     */
    private Integer role;


    public UserRole() {
    }

    public UserRole(java.lang.Integer id) {
        this.id = id;
    }

    public UserRole(UserRole origin) {
        this.id = origin.id;
        this.uid = origin.uid;
        this.role = origin.role;
    }

    public UserRole withId(Integer id) {
        this.id = id;
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public UserRole withUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return this.uid;
    }

    public UserRole withRole(Integer role) {
        this.role = role;
        return this;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "Id='" + id + '\'' +
                ",Uid='" + uid + '\'' +
                ",Role='" + role + '\'' +
                '}';
    }

}

