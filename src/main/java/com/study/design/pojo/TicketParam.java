package com.study.design.pojo;

public class TicketParam {
    private String account; // 真实的项目里，我们的account是set到用户的请求头里的 ，header里。过我们的网管的时候就已经记录了
    private String title;
    private String bankInfo;
    private String productId;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
