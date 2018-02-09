package com.example.demo.web;

import com.example.demo.common.dto.LeftJoinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    /*@Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;*/


    @RequestMapping(path = {"/"}, method = {RequestMethod.GET})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");

        // 在线条件
        /*UserQuery query = new UserQuery().withStatus(1);
        // 左连接条件,必须是高级会员,即role为1
        LeftJoinDTO leftJoinDTO = new LeftJoinDTO("user_role","uid","user","id","role",1);
        query.withLeftJoinDTO(leftJoinDTO);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andStatusEqualTo(1);
        userService.selectByExample(userExample);*/
        return mv;
    }

}
