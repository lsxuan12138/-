package com.hitfriend.dc.mapper;

import com.hitfriend.dc.po.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    /**
     * 通过id查找新闻
     * */
    News findNewsById(@Param("news_id") int news_id,@Param("type") int type);
    /**
     * 返回符合条件的记录的数量
     * */
    int getNewsByKeywordCount(@Param("keyword")String keyword );
    /**
     * 按条件分页查询
     * 返回符合条件的记录
     * */
    List<News> getNewsByKeyword(@Param("begin")int begin, @Param("pageSize")int pageSize, @Param("keyword")String keyword);
}
