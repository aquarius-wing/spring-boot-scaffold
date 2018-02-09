package com.example.demo.service.impl;

import com.example.demo.common.annotation.BaseService;
import com.example.demo.common.base.BaseServiceImpl;
import com.example.demo.dao.mapper.UserRoleMapper;
import com.example.demo.dao.model.UserRole;
import com.example.demo.dao.model.UserRoleExample;
import com.example.demo.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UserRoleService实现
* Created by shuzheng on 2018/2/9.
*/
@Service
@Transactional
@BaseService
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole, UserRoleExample> implements UserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Autowired
    UserRoleMapper userRoleMapper;

}