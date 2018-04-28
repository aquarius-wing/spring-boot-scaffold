package com.example.demo;

import com.example.demo.common.listener.ApplicationContextListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.demo")
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
    ApplicationContextListener getApplicationContextListener(){
        return new ApplicationContextListener();
    }
}
