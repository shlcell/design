package com.study.design.service;

import com.study.design.auditlog.OrderLogProcessor;
import com.study.design.auditlog.PayLogProcessor;
import com.study.design.order.pojo.Order;
import com.study.design.order.pojo.OrderState;
import com.study.design.order.pojo.OrderStateChangeAction;
import com.study.design.pay.facade.StrategyFacade;
import com.study.design.pay.pojo.PayBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private StateMachine<OrderState, OrderStateChangeAction> orderStateMachine;

    @Autowired
    private StateMachinePersister<OrderState, OrderStateChangeAction, Order> stateMachinePersister;

    @Autowired
    private PayLogProcessor payLogProcessor;

    @Autowired
    private OrderLogProcessor orderLogProcessor;

    //模拟一个存储
    private List<Object> orders = new ArrayList<>();

    public Order createOrder(Integer oid) {
        Order order = new Order();
        order.setOrderState(OrderState.ORDER_WAIT_PAY);
        order.setOrderId(oid);
        // 创建的order是不是得入库啊？如果不入库，下次访问的时候，是不是找不到了啊。
        orders.add(order); // 模拟存储到 db
        orderLogProcessor.processAuditLog("acccount", "createOrder", oid.toString());
        return order;
    }

    // 将来我们删除或者增加或者修改任何关于付款的模块，无需改动service。
    //不会对调用层产生任何代码的改动。
    // 调用层使用我们的pay 模块，无需关系实现的逻辑，只需要将入参传给我们的pay模块即可。
    public Order pay(PayBody payBody) {
        // 书写我们的付款逻辑
        boolean flag = false;
        flag = StrategyFacade.pay(payBody);
        if(flag) {
            Order order = (Order) orders.get(0); // 模拟查询db代码
            payLogProcessor.processAuditLog(payBody.getAccount(), "pay", order.getOrderId().toString());
            Message message = MessageBuilder
                    .withPayload(OrderStateChangeAction.PAY_ORDER).setHeader("order", order).build();
            //发送消息，发送给谁？和状态机有没有关系啊？ 有
            if(changeStateAction(message,order)) {
                return order;
            }
            //如果是 true，我们要保存到db
            saveToDb(payBody);
        }
        return null;
    }
    private void saveToDb(PayBody payBody) {
    }

    public Order send(Integer oid) {
        Order order = (Order) orders.get(0); // 模拟查询db代码
        Message message = MessageBuilder
                .withPayload(OrderStateChangeAction.SEND_ORDER).setHeader("order", order).build();
        if(changeStateAction(message,order)) {
            return order;
        }
        return null;
    }

    public Order receive(Integer oid) {
        Order order = (Order) orders.get(0); // 模拟查询db代码
        Message message = MessageBuilder
                .withPayload(OrderStateChangeAction.RECEIVE_ORDER).setHeader("order", order).build();
        if(changeStateAction(message,order)) {
            return order;
        }
        return null;
    }

    private boolean changeStateAction(Message<OrderStateChangeAction> message, Order order) {
        try {
            orderStateMachine.start();
            stateMachinePersister.restore(orderStateMachine, order); // 待议
            boolean res = orderStateMachine.sendEvent(message);
            stateMachinePersister.persist(orderStateMachine, order); // 持久
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return false;
    }
}
