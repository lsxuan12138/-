package com.hitfriend.dc.mapper;


import com.hitfriend.dc.po.NewsDetail;
import org.apache.ibatis.annotations.Param;

public interface NewsDetailMapper {
    /**
     * 通过id查找新闻
     * */
    NewsDetail findNewsById(@Param("news_id") String news_id);
}
