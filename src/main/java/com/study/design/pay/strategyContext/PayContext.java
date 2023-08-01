package com.study.design.pay.strategyContext;

import com.study.design.pay.strategy.PayStrategy;
import com.study.design.pay.pojo.PayBody;

// 动态地给一个对象添加一些额外的职责. 就得在根儿上添加。
//PayContext 就是我们的被装饰者，因为我们想给payContext添加额外功能：平台币更新和红包
// 所以我们选择使用装饰者模式。被装饰者必须要有 接口或者抽象类。也就是类图中的
public class PayContext extends AbstractPayContext{
    private PayStrategy payStrategy;

    public PayContext(PayStrategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    @Override
    public Boolean execute(PayBody payBody) {
        return this.payStrategy.pay(payBody);
    }
}
