package com.example.demo.web;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true)
public class Aop {
    private Logger logger = LoggerFactory.getLogger(Aop.class);

    public Aop(){
        logger.info("Aop init");
    }

    @Before("execution(* com.example.demo.web.action..*Action.*(..))")
    public void permissionCheck(JoinPoint point) {
        logger.info("@Before：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        logger.info("@Before：参数为：" + Arrays.toString(point.getArgs()));
        logger.info("@Before：被织入的目标对象为：" + point.getTarget());
    }

    @After("execution(* com.example.demo.web.action..*Action.*(..))")
    public void after(JoinPoint point) {
        logger.info("@After：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        logger.info("@After：参数为：" + Arrays.toString(point.getArgs()));
        logger.info("@After：被织入的目标对象为：" + point.getTarget());
    }

}
