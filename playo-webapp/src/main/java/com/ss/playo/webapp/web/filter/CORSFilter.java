package com.ss.playo.webapp.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, Content-Type");
        response.setHeader("Access-Control-Expose-Headers", "Authorization, Origin, Content-Type");
        final HttpServletRequest request = (HttpServletRequest)servletRequest;
        System.out.println("request.getMethod()"+request.getMethod());
        if(!request.getMethod().equals( "OPTIONS")){
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
