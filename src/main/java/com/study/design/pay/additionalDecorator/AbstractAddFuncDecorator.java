package com.study.design.pay.additionalDecorator;

import com.study.design.pay.pojo.PayBody;
import com.study.design.pay.strategyContext.AbstractPayContext;

public abstract class AbstractAddFuncDecorator extends AbstractPayContext {
    // 这是我们的装饰器类。专门干装修的。 专门添加新功能的（平台币，红包）
    // 装饰的是谁啊？ 我作为一个称职的装饰器，我必须得知道装饰的是谁，不然我咋干活儿啊。
    private AbstractPayContext abstractPayContext = null;

    public AbstractAddFuncDecorator(AbstractPayContext abstractPayContext) {
        this.abstractPayContext = abstractPayContext;
    }

    // 开始干活儿
    //1. 老活儿。 支付。但是我又不能修改支付代码，也不能修改支付逻辑。
    @Override
    public Boolean execute(PayBody payBody) {
        return abstractPayContext.execute(payBody);
    }

    //2. 老活儿不合适，没你装饰器照样能execute。 新活儿：平台币，红包）
    public abstract void additionalFunction(PayBody payBody);
}
