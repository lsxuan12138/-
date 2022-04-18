package com.hitfriend.dc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义过滤器
 * */
public interface MyFilter {
    public void doFilter(HttpServletRequest req, HttpServletResponse resp,MyFilterChain chain);
}
