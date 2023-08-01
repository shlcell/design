package com.study.design.pay.strategyContext;

import com.study.design.pay.pojo.PayBody;

public abstract class AbstractPayContext {
    public abstract Boolean execute(PayBody payBody);
}
