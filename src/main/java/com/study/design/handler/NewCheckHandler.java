package com.study.design.handler;

import com.study.design.pojo.UserInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.List;

public class NewCheckHandler extends AbstractSuggestRequirementHandler{
    @Override
    public void processHandler(UserInfo userInfo, List<String> suggestLists) {
        //通过获取 userinfo 的 buyProducts 属性
        boolean newUser = userInfo.isNewUser();
        if(newUser) {
            suggestLists = new ArrayList<>(); // 特定的新用户奖励
        }
    }
}
