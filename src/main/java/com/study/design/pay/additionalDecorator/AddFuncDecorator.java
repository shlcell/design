package com.study.design.pay.additionalDecorator;

import com.study.design.pay.pojo.PayBody;
import com.study.design.pay.strategyContext.AbstractPayContext;

// abstractPayContext 不是共享的，因为有的小伙伴选择 zfb 支付，有的小伙伴选择wx支付，等等。。。
public class AddFuncDecorator extends AbstractAddFuncDecorator{
    public AddFuncDecorator(AbstractPayContext abstractPayContext) {
        super(abstractPayContext);
    }

    // 新活儿
    @Override
    public void additionalFunction(PayBody payBody) { // 共享的，跟支付策略没有任何关系
        String product = payBody.getProduct();
        // 从db里边获取 product的详细信息。
        // 从配置中心（redis缓存）里获取产品的更新策略。
        // 根据策略更新用户平台币 或（和） 发放红包。
        System.out.println("更新平台币成功，发送红包到用户优惠券模块成功。");
    }

    @Override
    public Boolean execute(PayBody payBody) { // 算共享的。共享的调用逻辑
        Boolean result = super.execute(payBody); //老活儿
        this.additionalFunction(payBody); // 新活儿. 新活儿的各种重试，失败补偿
        return result;
    }

}
