package com.study.design.handler;

import com.study.design.pojo.UserInfo;

import java.util.List;

// 责任链模式：投放业务
public abstract class AbstractSuggestRequirementHandler {
    abstract void processHandler(UserInfo userInfo, List<String> suggestLists);
}
