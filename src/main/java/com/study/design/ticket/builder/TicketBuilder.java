package com.study.design.ticket.builder;

public abstract class TicketBuilder<T> {
    public abstract void setParam(String account, String title);

    public abstract void setContent(String content); // 配置中心 或 DB 或缓存
    public abstract void setProduct(String product); // db
    public void setBankInfo(String bankInfo){}; //需要校验的，校验通过才能设置

    public abstract T buildTicket();
}
