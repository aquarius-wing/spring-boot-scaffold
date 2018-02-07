package com.example.demo.service;

import com.example.demo.common.base.BaseServiceMock;
import com.example.demo.dao.mapper.UserRoleMapper;
import com.example.demo.dao.model.UserRole;
import com.example.demo.dao.model.UserRoleExample;

/**
* 降级实现UserRoleService接口
* Created by shuzheng on 2018/1/16.
*/
public class UserRoleServiceMock extends BaseServiceMock<UserRoleMapper, UserRole, UserRoleExample> implements UserRoleService {

}
