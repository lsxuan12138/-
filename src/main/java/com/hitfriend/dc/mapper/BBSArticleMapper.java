package com.hitfriend.dc.mapper;

import com.hitfriend.dc.po.BBSArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BBSArticleMapper {
    void add(BBSArticle article);
    /**
     * 通过id查找文章
     */
    BBSArticle findArticleById(int id);
    /**
     * 返回符合条件的记录的数量
     * */
    int getArticleByKeywordCount(@Param("keyword")String keyword , @Param("type") int type);
    /**
     * 按条件分页查询
     * 返回符合条件的记录
     * */
    List<BBSArticle> getArticleByKeyword(@Param("begin")int begin, @Param("pageSize")int pageSize, @Param("keyword")String keyword, @Param("type") int type);
}
