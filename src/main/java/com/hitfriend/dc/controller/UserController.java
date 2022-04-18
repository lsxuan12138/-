package com.hitfriend.dc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitfriend.dc.po.User;
import com.hitfriend.dc.service.UserService;
import com.hitfriend.dc.service.UserServiceImpl;
import com.hitfriend.dc.util.*;
import com.hitfriend.dc.web.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@WebServlet("/login")
public class UserController {
    private UserService userService;
    private ObjectMapper objectMapper;
    public UserController() {
        this.userService = new UserServiceImpl();
        this.objectMapper = new ObjectMapper();
    }

//    public static void test(HttpServletRequest req, HttpServletResponse resp){
//        System.out.println("test");
//    }

    /**
     * 用户登录
     * @param
     * @return 登录结果
     * */
    public Map<String,String> login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String[] array = GetOpenidUtil.getOpenid(req);
        String openid = array[7];
        String sessionId = array[3];
        //System.out.println(openid);
        //try{

        //进行登录
        //openid = Md5Util.encode(openid);
        User user = userService.login(openid);

        HttpSession session = req.getSession();
        session.setAttribute("userInfo",user);
        //保存session
        SessionManager.saveSession(sessionId,session);

        user.setOpenid("");
        String userInfo = objectMapper.writeValueAsString(user);
        //向用户告知登录成功，并返回一个令牌
        Map<String,String> map = new HashMap<String,String>();
        map.put("token",sessionId);
        map.put("data",userInfo);

        return map;
    }
    /**
     * 用户添加
     *
     */
    public void addUser(HttpServletRequest req, HttpServletResponse resp){
        String[] params = {"avatarUrl","city","country","gender","language","nickName","province"};
        V.vaild(req,params);
        //根据参数创建对象
        User user = V.entity(req,User.class,params);
        String[] array = GetOpenidUtil.getOpenid(req);
        String openid = array[7];
        String sessionId = array[3];
        user.setOpenid(openid);
        user.setTitle(Global.ROLE_STUDENT);
        //这里默认已验证
        //若接入校方数据库，将默认值改为USER_VER_OFF
        user.setStatus(Global.USER_VER_ON);
        userService.addUser(user);
    }
    /**
     * 由id查询用户信息
     * */
    public User findUserById(HttpServletRequest req, HttpServletResponse resp){
        String idStr = req.getParameter("id");

        int id = Integer.parseInt(idStr);

        User user = userService.findUserById(id);
        return  user;
    }
    /**
     * 删除用户
     *逻辑上
     */
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp){
        String[] params = {"openid"};
        V.vaild(req,params);

        String openid = req.getParameter("openid");
        userService.deleteUser(openid);
    }
    /**
     * 修改用户数据
     *
     */
    public int updateUser(HttpServletRequest req, HttpServletResponse resp){
        String[] Params = {"id","openid","avatarUrl","city","country","gender","language",
                "nickName","realName","province","status","time","number","title"};
        User user= V.entity(req,User.class,Params);
        userService.updateUser(user);
        return  0;
    }/**
     * 分页显示用户数据
     *
     */
    public  Page<User> list(HttpServletRequest req, HttpServletResponse resp){
        String pageNumStr = req.getParameter("pageNum") == null ? "1": req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize") == null ? "10": req.getParameter("pageSize");
        String keyword = req.getParameter("keyword") == null? "" : req.getParameter("keyword");

        int pageNum = Integer.parseInt(pageNumStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        Page<User> data = userService.list(pageNum,pageSize,keyword);
        return data;

     }
}
