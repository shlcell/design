package com.study.design.pay.strategy;

import com.study.design.pay.pojo.PayBody;

public class WcPayStrategy implements PayStrategy{
    @Override
    public Boolean pay(PayBody payBody) {
        // 支付细节省略
        return true;
    }
}
