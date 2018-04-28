
package com.example.demo.web.action;

import com.example.demo.common.dto.LeftJoinDTO;
import com.example.demo.common.dto.ResultDTO;
import com.example.demo.dao.mapper.UserMapperExtend;
import com.example.demo.dao.model.User;
import com.example.demo.dao.model.UserExample;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserAction {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserMapperExtend userMapperExtend;

    @GetMapping(path = {"/"})
    public ResultDTO index(){
        ModelAndView mv = new ModelAndView("index");

        LeftJoinDTO leftJoinDTO = new LeftJoinDTO("user_role","uid","user","id","role","1");
        UserExample userExample = new UserExample();
        userExample.withLeftJoin(leftJoinDTO);
        userExample.createCriteria().andStatusEqualTo(1);

        List<User> userList = userService.selectByExample(userExample);
        return ResultDTO.success(userList);
    }

    @GetMapping("getUserList")
    public ResultDTO<List<User>> getUserList(){
        return ResultDTO.success(userService.selectByExample(new UserExample()));
    }

    @GetMapping("searchUserList")
    public ResultDTO<List<User>> getUserList(String uname){
        return ResultDTO.success(userMapperExtend.searchUserName(uname));
    }
}

