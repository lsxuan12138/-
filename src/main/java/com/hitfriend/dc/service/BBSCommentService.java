package com.hitfriend.dc.service;

import com.hitfriend.dc.po.BBSComment;
import com.hitfriend.dc.web.Page;

public interface BBSCommentService {
    void add(BBSComment comment);
    Page<BBSComment>  list(int pageNum,int pageSize,int articleId);
}
