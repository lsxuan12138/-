package com.hitfriend.dc.po;

import java.sql.Timestamp;

public class BBSComment {
    //
    private int ComId;
    //评论内容
    private String ComContent;
    //文章id
    private int ComArtId;
    //评论者id
    private User user;
    //评论时间
    private Timestamp ComTime;

    @Override
    public String toString() {
        return "BBSComment{" +
                "ComId=" + ComId +
                ", ComContent='" + ComContent + '\'' +
                ", ComArtId=" + ComArtId +
                ", user=" + user +
                ", ComTime=" + ComTime +
                '}';
    }

    public int getComId() {
        return ComId;
    }

    public void setComId(int comId) {
        ComId = comId;
    }

    public String getComContent() {
        return ComContent;
    }

    public void setComContent(String comContent) {
        ComContent = comContent;
    }

    public int getComArtId() {
        return ComArtId;
    }

    public void setComArtId(int comArtId) {
        ComArtId = comArtId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getComTime() {
        return ComTime;
    }

    public void setComTime(Timestamp comTime) {
        ComTime = comTime;
    }
}
