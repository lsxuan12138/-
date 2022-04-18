package com.hitfriend.dc.po;

import java.sql.Timestamp;

public class BBSArticle {
    //
    private int artId;
    //发表者id
    private User user;
    //文章标题
    private String artTitle;
    //文章类型
    private int artType;
    //文章内容
    private String artContent;
    //文章创建/修改时间
    private Timestamp artCreTime;
    //浏览量
    private int artView;
    //点赞量
    private int artLikeNum;
    //图片地址
    private String artImgUrl;


    @Override
    public String toString() {
        return "BBSArticle{" +
                "artId=" + artId +
                ", user=" + user +
                ", artTitle='" + artTitle + '\'' +
                ", artType=" + artType +
                ", artContent='" + artContent + '\'' +
                ", artCreTime=" + artCreTime +
                ", artView=" + artView +
                ", artLikeNum=" + artLikeNum +
                ", artImgUrl='" + artImgUrl + '\'' +
                '}';
    }

    public String getArtImgUrl() {
        return artImgUrl;
    }

    public void setArtImgUrl(String artImgUrl) {
        this.artImgUrl = artImgUrl;
    }

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setArtCreTime(Timestamp artCreTime) {
        this.artCreTime = artCreTime;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public int getArtType() {
        return artType;
    }

    public void setArtType(int artType) {
        this.artType = artType;
    }

    public String getArtContent() {
        return artContent;
    }

    public void setArtContent(String artContent) {
        this.artContent = artContent;
    }

    public Timestamp getArtCreTime() {
        return artCreTime;
    }

    public void setArtCreTIme(Timestamp artCreTime) {
        this.artCreTime = artCreTime;
    }

    public int getArtView() {
        return artView;
    }

    public void setArtView(int artView) {
        this.artView = artView;
    }

    public int getArtLikeNum() {
        return artLikeNum;
    }

    public void setArtLikeNum(int artLikeNum) {
        this.artLikeNum = artLikeNum;
    }
}
