package com.study.design.service;

import com.study.design.handler.SuggestRequirementHandlerProcess;
import com.study.design.pojo.TicketParam;
import com.study.design.pojo.UserInfo;
import com.study.design.ticket.builder.CompanyTicketBuilder;
import com.study.design.ticket.builder.PersonalTicketBuilder;
import com.study.design.ticket.builder.TicketBuilder;
import login.implementor.LoginFunc;
import login.implementor.WbLoginFunc;
import login.implementor.abstractlogin.AbstractLoginProcessor;
import login.implementor.abstractlogin.ThirdPartLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private SuggestRequirementHandlerProcess suggestRequirementHandlerProcess;

    public List<String> suggestRequirement(String username) {
        //获取用户信息。因为用户已经登录了，那么user的信息是保存在我们的缓存里的。
        UserInfo userInfo = getUserInfo(username);
        List<String> results = new ArrayList<>();
        // 你可以发现，调用方无需关心任何 handler。完全对其屏蔽的。而且是
        // 完全解耦
        suggestRequirementHandlerProcess.process(userInfo, results);
        return results;
    }

    //因为这部分是需要查询 缓存（如果缓存没有，需要查库）不应该写到 service层，但是为了代码书写简便
    //这部分我模拟在service 层。
    private UserInfo getUserInfo(String username) {
        return new UserInfo();
    }

    public Object getTicket(TicketParam ticketParam) {
        TicketBuilder builder = null;
        String bankInfo = null;
        if(ticketParam.getBankInfo() != null) {
            bankInfo = "from 3rd party interface check.";
            builder = new CompanyTicketBuilder();
        } else {
            builder = new PersonalTicketBuilder();
        }
        builder.setParam(ticketParam.getAccount(), ticketParam.getTitle());
        String content = "from config center";
        String product = "from db";
        builder.setContent(content);
        builder.setProduct(product);
        builder.setBankInfo(bankInfo);
        // 详细的逻辑细节控制以及从配置中心或者是db中获取的逻辑步骤就是简单的 crud，自己明白即可
        return  builder.buildTicket();
    }

    public Boolean login(String name, String pwd, String type) {

        // 这部分我就不进行封装了，直接在 service里做了。小伙伴可以自行实现封装，类似享元。
        // 这部分是桥接模式的实现。
//        if(type.equals("wb")) {
//            LoginFunc func = new WbLoginFunc();
//            AbstractLoginProcessor processor = new ThirdPartLogin(func);
//            return processor.loginExecute(name, pwd, type);
//        }

        return true;
    }
}
