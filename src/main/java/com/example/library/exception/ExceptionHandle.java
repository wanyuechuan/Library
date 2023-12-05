package com.example.library.exception;

import cn.hutool.core.util.StrUtil;
import com.example.library.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionError(Exception e){
        log.error(""+e);


        return Result.error("系统错误");
    }


    // 捕获了该异常 进行处理
    @ExceptionHandler(value = ServiceException.class)
    public Result serviceExceptionError(ServiceException e){
        log.error(""+e);
        String code = e.getCode();
        if (StrUtil.isNotBlank(code)){
            return Result.error(code,e.getMessage());
        }

        return Result.error(e.getMessage());
    }
}
