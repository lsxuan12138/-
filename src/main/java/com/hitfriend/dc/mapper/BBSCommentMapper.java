package com.hitfriend.dc.mapper;

import com.hitfriend.dc.po.BBSArticle;
import com.hitfriend.dc.po.BBSComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BBSCommentMapper {
    /**
     * 发表评论
     * */
    void add(BBSComment comment);
    /**
     * 返回符合条件的记录的数量
     * */
    int getCommentByArtIdCount( int articleId);
    /**
     * 按条件分页查询
     * 返回符合条件的记录
     * */
    List<BBSComment> getCommentByArtId(@Param("begin")int begin, @Param("pageSize")int pageSize,@Param("articleId") int articleId);
}
