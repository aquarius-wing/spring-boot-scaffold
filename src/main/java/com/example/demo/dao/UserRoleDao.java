
 package com.example.demo.dao;

import com.example.demo.dao.base.BaseDao;
import com.example.demo.model.UserRole;
import com.example.demo.query.UserRoleQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by rapid-generator.
 */

 @Mapper
public interface UserRoleDao extends BaseDao<UserRole, UserRoleQuery>{

}
