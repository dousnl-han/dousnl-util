package com.dousnl.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/30 9:36
 */

/**
 * 第一种方式交给spring管理@Component
 */
@Component
/**
 * 第二种方式启动类加 @ServletComponentScan
 * filter加@WebFilter
 */
//@WebFilter
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("....first..filter:");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        if ("/".equals(requestURI)){
            System.out.println("url / do...chain");
            chain.doFilter(request,response);
            return;
        }
        System.out.println("......requestURI:"+requestURI);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("...first...destory filer....");
    }
}
