package com.study.design.ticket.pojo;

public class CompanyTicket implements Cloneable{
    private String type;
    private String footer;

    private String content; //配置中心 或者 DB里边获取。（缓存里获取，只不过如果万一DB 或配置中心有修改，需要同时更新缓存）
    private String product; //大部分情况db里获取（缓存里是有一些热门产品的）
    private String bankInfo; //校验我们的银行卡信息（通过我们的第三方银行相关接口进行的校验）

    private String account;
    private String title;

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

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    @Override
    public CompanyTicket clone() {
        CompanyTicket companyTicket = null;
        try {
            companyTicket = (CompanyTicket) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return companyTicket;
    }
}
