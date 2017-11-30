
 package com.example.demo.model;

import java.util.Date;

/**
 * Created by rapid-generator.
 */
public class User {

    /**
     * 描述: id
     * 字段: id  int(10)
     */
    private Integer id;

    /**
     * 描述: uname
     * 字段: uname  varchar(255)
     */
    private String uname;

    /**
     * 描述: password
     * 字段: password  varchar(255)
     */
    private String password;

    /**
     * 描述: 0为未登录,1为已登录
     * 字段: status  int(10)
     */
    private Integer status;


    public User() {
    }

    public User(java.lang.Integer id) {
        this.id = id;
    }

    public User(User origin) {
        this.id = origin.id;
        this.uname = origin.uname;
        this.password = origin.password;
        this.status = origin.status;
    }

    public User withId(Integer id) {
        this.id = id;
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public User withUname(String uname) {
        this.uname = uname;
        return this;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return this.uname;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public User withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + id + '\'' +
                ",Uname='" + uname + '\'' +
                ",Password='" + password + '\'' +
                ",Status='" + status + '\'' +
                '}';
    }

}

