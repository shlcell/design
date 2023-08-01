package com.study.design.handler;

import com.study.design.pojo.UserInfo;

import java.util.List;

public abstract class AbstractSuggestRequirementHandler {
    abstract void processHandler(UserInfo userInfo, List<String> suggestLists);
}
