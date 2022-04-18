package com.hitfriend.dc.controller;

import com.hitfriend.dc.po.News;
import com.hitfriend.dc.po.User;
import com.hitfriend.dc.service.NewsService;
import com.hitfriend.dc.service.NewsServiceImpl;
import com.hitfriend.dc.web.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewsController {
    private NewsService newsService;

    public NewsController() {
        this.newsService = new NewsServiceImpl();
    }

    public News getNews(HttpServletRequest req, HttpServletResponse resp){
        String idStr = req.getParameter("id");
        String typeStr = req.getParameter("type") == null? "0" : req.getParameter("type");
        int id = Integer.parseInt(idStr);
        int type = Integer.parseInt(typeStr);
        News news = newsService.findNewsById(id,type);
        return news;
    }

    public Page<News> list(HttpServletRequest req, HttpServletResponse resp){
        String pageNumStr = req.getParameter("pageNum") == null ? "1": req.getParameter("pageNum");
        String pageSizeStr = req.getParameter("pageSize") == null ? "10": req.getParameter("pageSize");
        String keyword = req.getParameter("keyword") == null? "" : req.getParameter("keyword");
        //String typeStr = req.getParameter("type") == null? "0" : req.getParameter("type");


        int pageNum = Integer.parseInt(pageNumStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        //int type = Integer.parseInt(typeStr);

        Page<News> data = newsService.list(pageNum,pageSize,keyword);
        return data;

    }
}
