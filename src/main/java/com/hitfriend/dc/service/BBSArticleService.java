package com.hitfriend.dc.service;

import com.hitfriend.dc.po.BBSArticle;
import com.hitfriend.dc.web.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BBSArticleService {
     BBSArticle findArticleById(int id);

     Page<BBSArticle> list(int pageNum, int pageSize, String keyword, int type);

     void add(BBSArticle article);
}
