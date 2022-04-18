package com.hitfriend.dc.po;

public class NewsDetail {
    private String newsId;
    private String detail;

    @Override
    public String toString() {
        return "NewsDetail{" +
                "newsId='" + newsId + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
