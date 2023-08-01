package com.study.design.pay.factory;

import com.study.design.pay.strategy.PayStrategy;
import com.study.design.pay.strategyContext.AbstractPayContext;
import com.study.design.pay.strategyContext.PayContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PayContextFactory {

    public final static Map<PayStrategy, AbstractPayContext> maps = new ConcurrentHashMap<>();

    public static AbstractPayContext getPayContext(PayStrategy payStrategy) {
        if(maps.get(payStrategy) == null) {
            PayContext payContext = new PayContext(payStrategy);
            maps.put(payStrategy, payContext);
        }
        return maps.get(payStrategy);
    }
}
