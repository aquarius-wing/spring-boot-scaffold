
 package com.example.demo.dao;

import com.example.demo.dao.base.BaseDao;
import com.example.demo.model.User;
import com.example.demo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by rapid-generator.
 */

@Mapper
public interface UserDao extends BaseDao<User, UserQuery>{

}
