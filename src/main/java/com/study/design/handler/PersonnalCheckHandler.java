package com.study.design.handler;

import com.study.design.pojo.UserInfo;

import java.util.List;

public class PersonnalCheckHandler extends AbstractSuggestRequirementHandler{
    @Override
    public void processHandler(UserInfo userInfo, List<String> suggestLists) {
        // 通过个人资质的check，我们找到了 4 个可以投放的业务。放到 suggestLists 中。
        suggestLists.add("1");
        suggestLists.add("2");
        suggestLists.add("3");
        suggestLists.add("4");
    }
}
