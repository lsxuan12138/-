package com.hitfriend.dc.service;

import com.hitfriend.dc.po.News;
import com.hitfriend.dc.po.User;
import com.hitfriend.dc.web.Page;

public interface NewsService {
    /**
     * 通过id查询新闻信息
     * */
    News findNewsById(int newsId,int newsType);
    /**
     * 分页查询用户数据
     * @param PageNum 页码
     * @param PageSize 每页数据量
     * */
    Page<News> list(int PageNum, int PageSize , String keyword);
}
