package com.hitfriend.dc.po;

import javax.swing.*;
import java.sql.Timestamp;

public class Media {
    private int id;
    private String fileDesc;
    private String url;
    private User user;
    private int type;
    private Timestamp createTime;

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", fileDesc='" + fileDesc + '\'' +
                ", url='" + url + '\'' +
                ", user=" + user +
                ", type=" + type +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
