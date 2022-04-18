package com.hitfriend.dc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class MyFilterChain {
    private List<MyFilter> filterChains;
    private int cur;
    public MyFilterChain() {
        this.filterChains = new ArrayList<MyFilter>();
        this.cur = 0;
    }

    /**
     * 执行过滤链
     */
    public void doFilter(HttpServletRequest req, HttpServletResponse resp){
        if(cur < filterChains.size()) {
            MyFilter filter = filterChains.get(cur);
            cur++;
            filter.doFilter(req, resp,this);
        }
    }
    /**
     * 添加过滤器
     * */
    public MyFilterChain addFilter(MyFilter filter){
        this.filterChains.add(filter);
        return this;
    }
}
