package com.hitfriend.dc.service;

import com.hitfriend.dc.mapper.NewsDetailMapper;
import com.hitfriend.dc.mapper.NewsMapper;
import com.hitfriend.dc.po.News;
import com.hitfriend.dc.po.NewsDetail;
import com.hitfriend.dc.util.D;
import org.apache.ibatis.session.SqlSession;

public class NewsDetailServiceImpl implements NewsDetailService{
    private NewsDetailMapper newsDetailMapper;
    private SqlSession session;
    public NewsDetailServiceImpl(){
        session= D.getConn();
        newsDetailMapper = session.getMapper(NewsDetailMapper.class);
    }

    @Override
    public NewsDetail findNewsById(String newsId) {

        NewsDetail news = newsDetailMapper.findNewsById(newsId);
        return news;
    }
}
