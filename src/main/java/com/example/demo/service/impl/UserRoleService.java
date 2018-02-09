


 package com.example.demo.service.impl;


import com.example.demo.dao.UserRoleDao;
import com.example.demo.dao.base.BaseDao;
import com.example.demo.model.UserRole;
import com.example.demo.query.UserRoleQuery;
import com.example.demo.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wing
 *
 */

 @Service("userRoleService")
public class UserRoleService extends BaseServiceImpl<UserRole, UserRoleQuery>{

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    protected BaseDao<UserRole, UserRoleQuery> getDao(){
        return userRoleDao;
    }
}
