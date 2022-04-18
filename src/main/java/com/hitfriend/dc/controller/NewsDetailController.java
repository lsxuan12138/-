package com.hitfriend.dc.controller;

import com.hitfriend.dc.po.News;
import com.hitfriend.dc.po.NewsDetail;
import com.hitfriend.dc.service.NewsDetailService;
import com.hitfriend.dc.service.NewsDetailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewsDetailController {
 private NewsDetailService newsDetailService;
 public NewsDetailController(){
     this.newsDetailService = new NewsDetailServiceImpl();
 }
    public NewsDetail getNews(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("newsid");

        NewsDetail news = newsDetailService.findNewsById(id);
        return news;
    }
}
