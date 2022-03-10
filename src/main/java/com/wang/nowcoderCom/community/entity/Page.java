package com.wang.nowcoderCom.community.entity;

public class Page {
    //当前页数
    private int current = 1;
    //显示上限
    private int limit = 10;
    //数据总数
    private int rows;
    //查询路径（用于复用分页的链接）
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current >= 1)
            this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit >= 1 && limit <= 100)
            this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >= 0)
            this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //数据库查询时需要使用的
    //获取当前页的起始行
    public int getOffset(){
        return (current - 1) * limit;
    }
    //获取总分页数
    public int getTotal(){
        //rows / limit
        if(rows % limit == 0) return rows / limit;
        else return rows / limit + 1;

    }

    public int getFrom(){
        return (current - 2 > 1 ) ? current - 2 : 1;
    }

    public int getTo(){
        return (current + 2 < getTotal() ) ? current + 2 : getTotal();
    }
}
