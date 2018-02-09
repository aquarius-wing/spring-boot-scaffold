


 package com.example.demo.service.impl;


import com.example.demo.dao.UserDao;
import com.example.demo.dao.base.BaseDao;
import com.example.demo.model.User;
import com.example.demo.query.UserQuery;
import com.example.demo.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wing
 *
 */

 @Service("userService")
public class UserService extends BaseServiceImpl<User, UserQuery>{

    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<User, UserQuery> getDao(){
        return userDao;
    }
}
