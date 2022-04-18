package com.hitfriend.dc.po;

import java.sql.Timestamp;

public class News {
    private int id;
    private String newsId;
    private String newsTitle;
    private String newsDepart;
    private String newsDate;

    @Override
    public String toString() {
        return "News{" +
                "newsId='" + newsId + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsDepart='" + newsDepart + '\'' +
                ", newsDate='" + newsDate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDepart() {
        return newsDepart;
    }

    public void setNewsDepart(String newsDepart) {
        this.newsDepart = newsDepart;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }
}
