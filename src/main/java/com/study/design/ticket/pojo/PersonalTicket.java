package com.study.design.ticket.pojo;

public class PersonalTicket implements Cloneable{
    private String type;
    private String footer;

    private String content; //配置中心 或者 DB里边获取。（缓存里获取，只不过如果万一DB 或配置中心有修改，需要同时更新缓存）
    private String product; //大部分情况db里获取（缓存里是有一些热门产品的）

    private String account; // 接口入参获取
    private String title;  // 接口入参获取

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public PersonalTicket clone() {
        PersonalTicket personalTicket = null;
        try {
            personalTicket = (PersonalTicket) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return personalTicket;
    }
}
