package com.dousnl.config;

import com.dousnl.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/7/7 10:44
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handlerException(Exception ex){
        log.error(">>>>>全局异常捕获：" + ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public String handlerMyException(Exception ex){
        log.error(">>>>>全局自定义异常捕获：" + ex.getMessage(), ex);
        return ex.getMessage();
    }
}
