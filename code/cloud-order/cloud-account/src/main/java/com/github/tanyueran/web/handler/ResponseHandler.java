package com.github.tanyueran.web.handler;

import com.github.tanyueran.modal.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "com.github.tanyueran.web.controller")
@Slf4j
public class ResponseHandler implements ResponseBodyAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception e) {
        log.error("异常捕获：", e);
        String message = e.getMessage();
        ResponseResult result = new ResponseResult();
        result.setCode(100);
        result.setMsg(message);
        result.setData(false);
        return result;
    }

    // 处理参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("请求的参数异常捕获：", e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        ResponseResult result = new ResponseResult();
        String message = fieldError.getDefaultMessage();
        message = fieldError.getField() + ":" + message;
        result.setCode(101);
        result.setMsg(message);
        result.setData(false);
        return result;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResponseResult) {
            return body;
        }
        ResponseResult success = ResponseResult.success(body);
        return success;
    }
}
