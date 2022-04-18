package com.hitfriend.dc.privilege;

import com.hitfriend.dc.exception.NoPrivilegeException;
import com.hitfriend.dc.po.User;
import com.hitfriend.dc.util.E;
import com.hitfriend.dc.util.Global;
import com.hitfriend.dc.util.SessionManager;
import com.hitfriend.dc.web.MyFilter;
import com.hitfriend.dc.web.MyFilterChain;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements MyFilter {
    private Logger log = LogManager.getLogger(LoginFilter.class);
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
        log.info("登录验证");
        //获取token
        String token = req.getParameter("token");
        //验证token合法性
        if(token == null){
            throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE,"请登录");
        }
        log.info("获取token"+token);
        HttpSession session = SessionManager.getSession(token);

        if(session == null){
            throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE,"请登录");
        }
        //验证session是否过期
        long lastAccessTime = session.getLastAccessedTime();
        long curTime = System.currentTimeMillis();
        if((curTime - lastAccessTime) >= (session.getMaxInactiveInterval()*1000)){
            SessionManager.removeSession(token);
            throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE,"连接断开，请重新登录");
        }
        User user = (User)session.getAttribute("userInfo");
        if(user == null){
            throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE,"请登录");
        }
        //存储token
        req.setAttribute("token",token);

        String role = user.getTitle();
        if(Global.ROLE_ADMIN.equals(role)){
            log.info("管理员权限，无需验证");
            return;
        }
        chain.doFilter(req, resp);
    }
}
