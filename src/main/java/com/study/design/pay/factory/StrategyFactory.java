package com.study.design.pay.factory;

import com.study.design.pay.strategy.PayStrategy;
import com.study.design.pay.strategyEnum.StrategyEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//我们的工厂类依靠我们的策略枚举返回策略类
// 我们以后永远不需要修改我们的Factory。他是一个无状态的
public class StrategyFactory{
    //那个时候我们 引出他是为了讲解 单例模式，其实那个时候我们已经利用了单例模式
    //思想升级之后的享元，模式了。
    private static final Map<String, PayStrategy> strategyMaps = new ConcurrentHashMap();

    public static PayStrategy getPayStrategy(StrategyEnum strategyEnum) {

        PayStrategy payStrategy = strategyMaps.get(strategyEnum.getValue());
        if(payStrategy == null) {
            try {
                // 每次调用都需要进行一次反射。优化调整。
                payStrategy = (PayStrategy) Class.forName(strategyEnum.getValue()).newInstance();
                strategyMaps.put(strategyEnum.getValue(), payStrategy);
            } catch (Exception e) {
                //异常
            }
        }
        return payStrategy;
    }
    // 工厂模式的变种比较多。虽然这里边我写了一个 有静态方法的 工厂类，但是要与我们平时
    //写的 utils 类进行区分，utils 是以功能（生成uuid，日期转化）为导向的工具类。
    // 而 我写的factory类，他是以产出（一类行为类：策略）为导向的
}
