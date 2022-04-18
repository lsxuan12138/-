package com.hitfriend.dc.service;

import com.hitfriend.dc.mapper.BBSArticleMapper;
import com.hitfriend.dc.po.BBSArticle;
import com.hitfriend.dc.po.News;
import com.hitfriend.dc.util.D;
import com.hitfriend.dc.web.Page;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BBSArticleServiceImpl implements BBSArticleService{
    private BBSArticleMapper articleMapper;
    private SqlSession session;
    public BBSArticleServiceImpl(){
        session= D.getConn();
        articleMapper = session.getMapper(BBSArticleMapper.class);
    }

    @Override
    public void add(BBSArticle article) {
        articleMapper.add(article);
    }

    @Override
    public BBSArticle findArticleById(int id) {
        BBSArticle article = articleMapper.findArticleById(id);
        return article;
    }
    @Override
    public Page<BBSArticle> list(int pageNum, int pageSize, String keyword, int type) {
        int total = articleMapper.getArticleByKeywordCount(keyword,type);
        int begin = (pageNum-1) * pageSize;

        List<BBSArticle> datas= articleMapper.getArticleByKeyword(begin,pageSize,keyword,type);

        String brief;
        for(BBSArticle article : datas) {
            //System.out.println(str);
            if(article.getArtContent()==null)continue;
            if(article.getArtContent().length()>=51) {
                brief = article.getArtContent().substring(0, 50);
                article.setArtContent(brief);
            }
            switch (article.getArtType()){
                case 1:
                    article.setArtTitle("【日常讨论】 " + article.getArtTitle());
                    break;
                case 2:
                    article.setArtTitle("【学术交流】 " + article.getArtTitle());
                    break;
                case 3:
                    article.setArtTitle("【竞赛组队】 " + article.getArtTitle());
                    break;
            }
        }

        Page<BBSArticle> PageData = new Page<BBSArticle>(pageNum,pageSize,total,datas);
        return PageData;
    }
}
