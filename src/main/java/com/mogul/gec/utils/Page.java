package com.mogul.gec.utils;

import java.util.List;

public class Page {
    /**
     * 总记录数
     */
    public int total;
    /**
     * 每页记录数
     */
    public int pageSize;
    /**
     * 总页数
     */
    public int pages;
    /**
     * 当前页数
     */
    public int pageNum;
    /**
     * 列表数据
     */

    public int prePage;
    public int nextPage;
    public List<?> list;

    public Page(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.total = totalCount;
        this.pageSize = pageSize;
        this.pageNum = currPage;
        this.pages = (int)Math.ceil((double)totalCount/pageSize);
        prePage=1;nextPage=1;
        if(currPage>1) {
            this.prePage = currPage - 1;
        }
        if(currPage<pages) {
            this.nextPage = currPage + 1;
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
