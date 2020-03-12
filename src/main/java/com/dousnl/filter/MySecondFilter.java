package com.dousnl.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/30 13:46
 */
@Component
public class MySecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("....second..filter:");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        System.out.println("....second..requestURI:"+requestURI);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("...second...destory filer....");
    }
}
