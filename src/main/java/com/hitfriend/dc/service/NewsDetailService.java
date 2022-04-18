package com.hitfriend.dc.service;

import com.hitfriend.dc.mapper.NewsDetailMapper;
import com.hitfriend.dc.mapper.NewsMapper;
import com.hitfriend.dc.po.News;
import com.hitfriend.dc.po.NewsDetail;
import com.hitfriend.dc.util.D;
import org.apache.ibatis.session.SqlSession;

public interface NewsDetailService {

    /**
     * 通过id查询新闻信息
     * */
    NewsDetail findNewsById(String newsId);

}
