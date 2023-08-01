package com.study.design.pay.proxy;

import com.study.design.pay.pojo.PayBody;
import com.study.design.pay.strategyContext.AbstractPayContext;

public class ContextProxy extends AbstractPayContext {

    private AbstractPayContext abstractPayContext = null;

    public ContextProxy(AbstractPayContext abstractPayContext) {
        this.abstractPayContext = abstractPayContext;
    }

    @Override
    public Boolean execute(PayBody payBody) {
        return null;
    }
}
