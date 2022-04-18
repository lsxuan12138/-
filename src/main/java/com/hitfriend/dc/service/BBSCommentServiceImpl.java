package com.hitfriend.dc.service;

import com.hitfriend.dc.mapper.BBSCommentMapper;
import com.hitfriend.dc.po.BBSArticle;
import com.hitfriend.dc.po.BBSComment;
import com.hitfriend.dc.util.D;
import com.hitfriend.dc.web.Page;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BBSCommentServiceImpl implements BBSCommentService{
    private BBSCommentMapper commentMapper;
    private SqlSession session;
    public BBSCommentServiceImpl(){
        session= D.getConn();
        commentMapper=session.getMapper(BBSCommentMapper.class);
    }

    @Override
    public void add(BBSComment comment) {
        commentMapper.add(comment);
    }

    @Override
    public Page<BBSComment> list(int pageNum, int pageSize, int articleId){
        int total = commentMapper.getCommentByArtIdCount(articleId);
        int begin = (pageNum-1) * pageSize;

        List<BBSComment> datas= commentMapper.getCommentByArtId(begin,pageSize,articleId);

        Page<BBSComment> PageData = new Page<BBSComment>(pageNum,pageSize,total,datas);
        return PageData;
    }
}
