package com.hitfriend.dc.controller;

import com.hitfriend.dc.po.BBSArticle;
import com.hitfriend.dc.po.User;
import com.hitfriend.dc.service.BBSArticleService;
import com.hitfriend.dc.service.BBSArticleServiceImpl;
import com.hitfriend.dc.util.SessionManager;
import com.hitfriend.dc.util.V;
import com.hitfriend.dc.web.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BBSArticleController {
    private BBSArticleService bbsArticleService;

    public BBSArticleController() {
        this.bbsArticleService = new BBSArticleServiceImpl();
    }

    /**
     *
     * 发表文章
     * */
    public void add(HttpServletRequest req, HttpServletResponse resp){
        String token=req.getParameter("token");
        String[] mustParams={"artTitle","artType","artContent"};
        V.vaild(req,mustParams);

        String[] params={"artTitle","artType","artContent","artImgUrl"};
        BBSArticle article = V.entity(req,BBSArticle.class,params);

        //HttpSession session = req.getSession();
        HttpSession session = SessionManager.getSession(token);
        User user = (User) session.getAttribute("userInfo");

        article.setUser(user);

        bbsArticleService.add(article);
    }
    /**
     * 通过id查找文章
     * */
    public BBSArticle getBBSArticle(HttpServletRequest req, HttpServletResponse resp){
        String idStr = req.getParameter("id");
        //String typeStr = req.getParameter("type") == null? "0" : req.getParameter("type");
        int id = Integer.parseInt(idStr);
        //int type = Integer.parseInt(typeStr);
        BBSArticle article = bbsArticleService.findArticleById(id);
        return article;
    }
    /**
     * 分页查找
     * */
    public Page<BBSArticle> list(HttpServletRequest req, HttpServletResponse resp){
        String pageNumStr = req.getParameter("pageNum") == null ? "1": req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize") == null ? "10": req.getParameter("pageSize");
        String keyword = req.getParameter("keyword") == null? "" : req.getParameter("keyword");
        String typeStr = req.getParameter("type") == null? "0" : req.getParameter("type");


        int pageNum = Integer.parseInt(pageNumStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        int type = Integer.parseInt(typeStr);

        Page<BBSArticle> data = bbsArticleService.list(pageNum,pageSize,keyword,type);
        return data;

    }
}
