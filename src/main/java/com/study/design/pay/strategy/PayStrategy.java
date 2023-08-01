package com.study.design.pay.strategy;

import com.study.design.pay.pojo.PayBody;

// 增加一个付款方式的话，需要写一个 strategy的子类实现类，类似Wc。
// ①枚举类增加一个枚举，就一行代码。
// ②门面类 中枚举获取的修改。

// 扩展性良好，而且不影响 调用端。我们的任何改动，不需要让调用端知道。
public interface PayStrategy {

    Boolean pay(PayBody payBody);
    // 之所以使用interface,是因为我们的所有的策略接口下的方法都是需要复写的，
    // 没有公共的、可重用的方法。

    //abstract class 他能够有自己的 抽象方法（需要子类复写的）
    // abstract class 他也能够有自己的可执行方法（普通的方法，不抽象的）
}
