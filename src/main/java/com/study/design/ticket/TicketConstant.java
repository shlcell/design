package com.study.design.ticket;

import com.study.design.ticket.pojo.CompanyTicket;
import com.study.design.ticket.pojo.PersonalTicket;

public class TicketConstant {
    // 保存只具有公共属性的对象，供clone使用。
    public static PersonalTicket personalTicket = new PersonalTicket();
    public static CompanyTicket companyTicket = new CompanyTicket();

    static {
        personalTicket.setType("type");
        personalTicket.setFooter("footer");
        companyTicket.setType("type");
        companyTicket.setFooter("footer");
    }
}
