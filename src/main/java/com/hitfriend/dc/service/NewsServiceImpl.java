package com.hitfriend.dc.service;

import com.hitfriend.dc.mapper.NewsMapper;
import com.hitfriend.dc.po.News;
import com.hitfriend.dc.util.D;
import com.hitfriend.dc.web.Page;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class NewsServiceImpl implements NewsService{
    private NewsMapper newsMapper;
    private SqlSession session;
    public NewsServiceImpl(){
        session= D.getConn();
        newsMapper = session.getMapper(NewsMapper.class);
    }

    @Override
    public News findNewsById(int newsId,int newsType) {
        News news = newsMapper.findNewsById(newsId,newsType);
        return news;
    }

    @Override
    public Page<News> list(int pageNum, int pageSize, String keyword) {
        int total = newsMapper.getNewsByKeywordCount(keyword);
        int begin = (pageNum-1) * pageSize;

        List<News> datas= newsMapper.getNewsByKeyword(begin,pageSize,keyword);

//        String brief;
//        for(News news : datas) {//其内部实质上还是调用了迭代器遍历方式，这种循环方式还有其他限制，不建议使用。
//            //System.out.println(str);
//            if(news.getNewsDetail()==null)continue;
//            if(news.getNewsDetail().length()>=51) {
//                brief = news.getNewsDetail().substring(0, 50);
//                news.setNewsDetail(brief);
//            }
//        }

        Page<News> PageData = new Page<News>(pageNum,pageSize,total,datas);
        return PageData;
    }
}
