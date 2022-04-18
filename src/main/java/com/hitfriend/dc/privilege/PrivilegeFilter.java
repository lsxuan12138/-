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
import java.util.ArrayList;
import java.util.List;

/**
 * 权限验证
 * */
public class PrivilegeFilter implements MyFilter {
    private static List<String> users;
    private Logger log = LogManager.getLogger(PrivilegeFilter.class);

    public PrivilegeFilter() {
        this.users = new ArrayList<String>();
        users.add("user/addUser");
        users.add("user/deleteUser");
        users.add("user/updateUser");
        //users.add("user/list");
        users.add("BBSArticle/add");
        users.add("BBSArticle/getBBSArticle");
        users.add("BBSComment/list");
        users.add("BBSComment/add");
        users.add("file/upload");
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
        String reqPath = (String) req.getAttribute(Global.REQ_PATH);

        String sid = (String)req.getParameter("token");
        HttpSession session = SessionManager.getSession(sid);

        User user = (User)session.getAttribute("userInfo");
        String role = user.getTitle();

        if(!Global.ROLE_STUDENT.equals(role)){
            throw new NoPrivilegeException(E.NO_PRIVILEGE_ERROR_CODE,E.NO_PRIVILEGE_ERROR_INFO);
        }
        if(!users.contains(reqPath)){
            throw new NoPrivilegeException(E.NO_PRIVILEGE_ERROR_CODE,E.NO_PRIVILEGE_ERROR_INFO);
        }
        log.info("通过权限验证");
        chain.doFilter(req, resp);
    }
}
