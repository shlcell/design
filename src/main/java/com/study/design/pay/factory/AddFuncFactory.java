package com.study.design.pay.factory;

import com.study.design.pay.additionalDecorator.AbstractAddFuncDecorator;
import com.study.design.pay.additionalDecorator.AddFuncDecorator;
import com.study.design.pay.strategyContext.PayContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AddFuncFactory {
    // 工厂的目的是生产 AddFuncDecorator； 生产几个啊？ 多个：享元
    // key 应该是能够和 AbstractAddFuncDecorator 做成对应的东西。
    // 这个map最多保存是三个对象。
    public final static Map<PayContext, AbstractAddFuncDecorator> maps = new ConcurrentHashMap<>();

    public static AbstractAddFuncDecorator getAddFunc(PayContext payContext) {
        if(maps.get(payContext) == null) {
            AddFuncDecorator addFuncDecorator = new AddFuncDecorator(payContext);
            maps.put(payContext, addFuncDecorator);
        }
        return maps.get(payContext);
    }
}
