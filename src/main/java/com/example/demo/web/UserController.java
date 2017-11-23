package com.example.demo.web;

import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(path = {"/"}, method = {RequestMethod.GET})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("userList", userService.getOnlineUserList());
        return mv;
    }
}
