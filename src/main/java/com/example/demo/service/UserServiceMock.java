package com.example.demo.service;

import com.example.demo.common.base.BaseServiceMock;
import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.dao.model.User;
import com.example.demo.dao.model.UserExample;

/**
* 降级实现UserService接口
* Created by shuzheng on 2018/2/9.
*/
public class UserServiceMock extends BaseServiceMock<UserMapper, User, UserExample> implements UserService {

}
