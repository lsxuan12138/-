package com.hitfriend.dc.privilege;

import com.hitfriend.dc.util.Global;
import com.hitfriend.dc.web.MyFilter;
import com.hitfriend.dc.web.MyFilterChain;
import com.hitfriend.dc.web.WebDispatcher;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 白名单验证
 * */
public class WhiteFilter implements MyFilter {
    private static List<String> whiteList;
    private Logger log = Logger.getLogger(WhiteFilter.class);
    static {
        whiteList = new ArrayList<String>();
        whiteList.add("user/login");
        whiteList.add("user/addUser");
        whiteList.add("news/list");
        whiteList.add("newsDetail/getNews");
        whiteList.add("BBSArticle/list");

    }
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
        String path = (String) req.getAttribute(Global.REQ_PATH);

        if(!whiteList.contains(path)){
            chain.doFilter(req,resp);
        }else {
            log.info("通过白名单认证");
        }
    }
}
