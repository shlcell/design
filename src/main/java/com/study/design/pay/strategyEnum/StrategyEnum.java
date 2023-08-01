package com.study.design.pay.strategyEnum;

public enum StrategyEnum {
    ZfbPayStrategy("com.study.design.pay.strategy.ZfbPayStrategy"),
    WcPayStrategy("com.study.design.pay.strategy.WcPayStrategy"),
    BkPayStrategy("com.study.design.pay.strategy.BkPayStrategy");
    String value = "";
    StrategyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
