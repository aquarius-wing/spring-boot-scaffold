package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.query.UserQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luowenyin
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public List<User> getOnlineUserList(){
        return userDao.selectListByCondition(new UserQuery().withStatus(1));
    }
}
