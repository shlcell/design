package com.study.design.ticket.builder;

import com.study.design.ticket.TicketConstant;
import com.study.design.ticket.pojo.PersonalTicket;

public class PersonalTicketBuilder extends TicketBuilder<PersonalTicket> {

    private PersonalTicket personalTicket = TicketConstant.personalTicket.clone(); // new 关键字 改成clone的形式，只clone我们的
    // 不可变部分。对于可变部分和自定义用户提交部分，不进行clone

    @Override
    public void setParam(String account, String title) {
        personalTicket.setTitle(title);
        personalTicket.setAccount(account);
    }

    @Override
    public void setContent(String content) {
        personalTicket.setContent(content);
    }

    @Override
    public void setProduct(String product) {
        personalTicket.setProduct(product);
    }

    @Override
    public PersonalTicket buildTicket() {
        return personalTicket;
    }
}
