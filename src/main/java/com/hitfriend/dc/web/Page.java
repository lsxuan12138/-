package com.hitfriend.dc.web;

import java.util.List;

public class Page<T> {
    //当前页码
    private int curPage;
    //页面数据量
    private int pageSize;
    //总数据量
    private int totalSize;
    //页码总数
    private int totalPage;
    //当页数据
    private List<T> data;

    public Page(int curPage, int pageSize, int totalSize, List<T> data) {
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.totalPage= (totalSize % pageSize == 0)?(totalSize / pageSize) : (totalSize / pageSize) + 1;
        this.data = data;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
