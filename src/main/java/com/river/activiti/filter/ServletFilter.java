package com.river.activiti.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: he.feng
 * @date: 9:32 2017/11/15
 * @desc:
 **/
@WebFilter(filterName = "ServletFilter",urlPatterns = "/*")
public class ServletFilter implements Filter {

    public static ThreadLocal<HttpServletRequest> localRequest = new ThreadLocal<HttpServletRequest>();
    public static ThreadLocal<HttpServletResponse> localResponse = new ThreadLocal<HttpServletResponse>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        localRequest.set((HttpServletRequest) servletRequest);
        localResponse.set((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
