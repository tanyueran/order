package com.github.tanyueran.web.handler;

import com.alibaba.fastjson.JSON;
import com.github.tanyueran.modal.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 处理认证过的用户访问无权限资源时的异常
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        log.info("无权访问异常：+++++++++++++++++++");
        log.error(e.getMessage(), e);
        // 可以针对不同的异常，返回响应的文字
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        httpServletResponse.setStatus(401);
        ResponseResult res = new ResponseResult();
        res.setCode(401);
        res.setMsg(e.getMessage());
        res.setData(false);
        writer.write(JSON.toJSONString(res));
        writer.close();
    }
}
