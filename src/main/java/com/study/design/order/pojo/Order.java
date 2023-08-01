package com.study.design.order.pojo;

//状态转化的一个控制机。状态机：初始化状态；配置我们的所有状态之间的转化关系；
//一些持久化的工作（redis）。
public class Order {
    private Integer orderId;
    private OrderState orderState;//订单状态

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}