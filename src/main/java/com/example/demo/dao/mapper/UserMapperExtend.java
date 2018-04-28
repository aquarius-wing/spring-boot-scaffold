package com.example.demo.dao.mapper;

import com.example.demo.dao.mapper.common.UserMapper;
import com.example.demo.dao.model.User;
import com.example.demo.dao.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapperExtend extends UserMapper {

    List<User> searchUserName(String uname);

}