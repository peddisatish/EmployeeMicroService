package com.example.demo.Logger;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class ApiLogger extends HandlerInterceptorAdapter {
    private static final Logger logger = Logger.getLogger(ApiLogger.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = UUID.randomUUID().toString();
        log(request, response, requestId);
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        request.setAttribute("requestId", requestId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        long startTime = (Long) request.getAttribute("startTime");

        long endTime = System.currentTimeMillis();

        long executeTime = endTime - startTime;
        logger.info(String.format("requestId %s, Handle :%s , request take time: %s", request.getAttribute("requestId"), handler, executeTime));
        System.out.println(String.format("requestId %s, Handle :%s , request take time: %s", request.getAttribute("requestId"), handler, executeTime));

    }

    private void log(HttpServletRequest request, HttpServletResponse response, String requestId) {
        logger.info(String.format("requestId %s, host %s  HttpMethod: %s, URI : %s", requestId, request.getHeader("host"),
                request.getMethod(), request.getRequestURI()));
        System.out.println(String.format("requestId %s, host %s  HttpMethod: %s, URI : %s", requestId, request.getHeader("host"),
                request.getMethod(), request.getRequestURI()));
    }
}