package com.hitfriend.dc.controller;

import com.hitfriend.dc.po.BBSArticle;
import com.hitfriend.dc.po.BBSComment;
import com.hitfriend.dc.po.User;
import com.hitfriend.dc.service.BBSCommentService;
import com.hitfriend.dc.service.BBSCommentServiceImpl;
import com.hitfriend.dc.util.SessionManager;
import com.hitfriend.dc.util.V;
import com.hitfriend.dc.web.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BBSCommentController {
    private BBSCommentService commentService;
    public BBSCommentController(){
        this.commentService=new BBSCommentServiceImpl();
    }
    public void add(HttpServletRequest req, HttpServletResponse resp){

        String token=req.getParameter("token");
        String[] params={"ComContent","ComArtId"};
        V.vaild(req,params);

        BBSComment comment = V.entity(req,BBSComment.class,params);

        //HttpSession session = req.getSession();
        HttpSession session = SessionManager.getSession(token);
        User user = (User) session.getAttribute("userInfo");

        comment.setUser(user);

        commentService.add(comment);
    }
    /**
     *
     * 通过ArticleId查询评论
     * */
    public Page<BBSComment> list (HttpServletRequest req, HttpServletResponse resp){
        String pageNumStr = req.getParameter("pageNum") == null ? "1": req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize") == null ? "10": req.getParameter("pageSize");
        String articleIdStr = req.getParameter("articleId") == null? "" : req.getParameter("articleId");

        int pageNum = Integer.parseInt(pageNumStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        int articleId = Integer.parseInt(articleIdStr);

        Page<BBSComment> data = commentService.list(pageNum,pageSize,articleId);

        return data;
    }
}
