package com.example.demo.test;


import com.example.demo.web.action.UserAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest
@WebAppConfiguration
public class WebTest {
    @Autowired
    private MockMvc mvc;


    @Test
    public void getUserList() throws Exception {
        RequestBuilder request = null;
        String json_str = null;
        request = get("/getUserList");
        json_str = mvc.perform(request).andReturn().getResponse().getContentAsString();
        System.out.println(json_str);
    }



}
