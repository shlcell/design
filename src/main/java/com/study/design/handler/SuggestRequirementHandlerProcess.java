package com.study.design.handler;

import com.study.design.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuggestRequirementHandlerProcess {

    @Value("#{'${suggest.requirement.handler}'.split(',')}")
    private List<String> handlers;

    public void process(UserInfo userInfo, List<String> suggestLists) {
        // 如果想要实时的进行顺序的调整或者是增减。那必须要使用配置中心进行配置。
        // 比如springcloud里边自带的 git 的这种配置中心； applo 配置中心。
        try {
            for(String handler : handlers) {
                AbstractSuggestRequirementHandler handle =
                        (AbstractSuggestRequirementHandler) Class.forName(handler).newInstance();
                handle.processHandler(userInfo, suggestLists);
            }
        } catch (Exception e) {

        }
    }
}
