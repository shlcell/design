package com.study.design.pay.facade;

import com.study.design.pay.additionalDecorator.AddFuncDecorator;
import com.study.design.pay.factory.AddFuncFactory;
import com.study.design.pay.pojo.PayBody;
import com.study.design.pay.strategy.PayStrategy;
import com.study.design.pay.strategyContext.PayContext;
import com.study.design.pay.strategyEnum.StrategyEnum;
import com.study.design.pay.factory.StrategyFactory;

// 最终我们只暴露我们的门面，对于里边的这些所有的工厂。策略啊。策略枚举啊，统统不暴露。
// 门面就是我们的超强封装。
public class StrategyFacade {
    // 定义一个map，将对应关系提前初始化好。
    // 双十一的时候，有大量的用户进行下单（千万级），就会造成千万级的pay接口的调用。
    // 很可惜，这部分代码里边有两个 new 关键字（new PayContext(payStrategy) 和 new AddFuncDecorator）。
    // 如果瞬时见。有几十万的并发进来，那么会创建几十万个 context对象和 addFunc对象，造成 年轻代的eden区的频繁对象创建
    // 虽然说调用完就进行了对象的垃圾收集，但是这么多的访问对象进来会造成，minorgc。
    // 1. 单例模式吗？PayContext创建是基于payStrategy，不止一种paycontext啊，用单例不行呀。 AddFuncDecorator 是基于PayContext
    // 创建的，PayContext 不止一种，AddFuncDecorator 也不止一种。
    // 2. 享元模式。享元模式是单例模式的一种思想升级。单例模式，针对的是同一种对象，没有任何不同的细节。而享元模式，针对多个对象。
    // 多个对象：同一种class，但是里边的属性有些许不同。PayContext是同一种对象吧？是的； PayContext 是不是有细节上的不同呢？是的，
    // payStrategy不同； 那么我们是否可以知道这个PayContext的种类数量呢？是的，目前有三种。 享元模式能够对这可控数量的有不同细节的
    //同一种class对象进行共享，保证我们的程序不频繁的创建对象。
    public static Boolean pay(PayBody payBody) {
        //获取我们的 策略枚举
        StrategyEnum strategyEnum = getStrategyEnum(payBody.getType());
        if(strategyEnum == null) {
            return false;
        }
        //获取我们的策略对象
        PayStrategy payStrategy = StrategyFactory.getPayStrategy(strategyEnum);
        //生成我们的策略上下文
        PayContext context = new PayContext(payStrategy); // TO DO
        // 装饰一下 context，。立马多了一个功能
        // 我看这行代码啊，就不顺眼。代理模式搞他。
        AddFuncDecorator addFuncDecorator = (AddFuncDecorator) AddFuncFactory.getAddFunc(context);
        //进行扣款
        return addFuncDecorator.execute(payBody);
    }

    private static StrategyEnum getStrategyEnum(int type) {
        switch (type) {
            case 0:
                return StrategyEnum.ZfbPayStrategy;
            case 1:
                return StrategyEnum.WcPayStrategy;
            case 2:
                return StrategyEnum.BkPayStrategy;
            default:
                return null;
        }
    }
}
