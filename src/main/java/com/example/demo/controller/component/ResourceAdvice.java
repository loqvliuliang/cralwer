package com.example.demo.controller.component;

import com.example.demo.exception.BizException;
import com.example.demo.exception.ExceptionService;
import com.example.demo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by 刘亮 on 2017/11/13.
 * 配置统一异常处理
 */
@ControllerAdvice
public class ResourceAdvice {

    @Autowired
    private ExceptionService exceptionService;

    @ExceptionHandler(BizException.class)
    public ResponseEntity<JsonResult> handleBizException(BizException e){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(JsonResult.ERROR);
        jsonResult.setErrorCode(e.getCode());
        String message = exceptionService.getMessageDetailByCode(e.getCode(),e.getArgs());
        jsonResult.setMsg(message);
        jsonResult.setData(e.getArgs());
        return new ResponseEntity<>(jsonResult, HttpStatus.BAD_REQUEST);
    }


}
