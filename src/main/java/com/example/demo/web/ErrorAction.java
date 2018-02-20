package com.example.demo.web;

import com.example.demo.common.dto.ResultDTO;
import com.example.demo.common.exception.ErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@RestController
@ControllerAdvice
public class ErrorAction {

    private Logger logger = LoggerFactory.getLogger(ErrorAction.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultDTO defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        e.printStackTrace();
        logger.error("内部捕捉");
        return ResultDTO.fail(e.getMessage());
    }
    @ExceptionHandler(value = ServletRequestBindingException.class)
    @ResponseBody
    public ResultDTO servletErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return ResultDTO.fail("token miss",-1);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDTO constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException exception) {
        ConstraintViolation constraintViolation = (ConstraintViolation)exception.getConstraintViolations().toArray()[0];
        exception.printStackTrace();
        return ResultDTO.fail("参数("+constraintViolation.getPropertyPath()+")错误:"+constraintViolation.getMessage(),2);
    }

    @ExceptionHandler(value = ErrorException.class)
    @ResponseBody
    public ResultDTO ErrorExceptionHandler(HttpServletRequest req, ErrorException e) throws Exception {
        e.printStackTrace();
        return ResultDTO.fail(e.getMessage(),e.getCode());
    }

}
