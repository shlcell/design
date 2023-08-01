package com.study.design.controller;

import com.study.design.order.pojo.Order;
import com.study.design.pay.pojo.PayBody;
import com.study.design.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping("mkOrder")
    public Order createOrder(@RequestParam Integer oid) {
        return orderService.createOrder(oid);
    }

    @PostMapping("/pay")
    public Order payOrder(@RequestBody PayBody payBody){
        return orderService.pay(payBody);
    }

    @GetMapping("/send")
    public Order send(@RequestParam Integer oid) {
        return orderService.send(oid);
    }

    @GetMapping("/receive")
    public Order receive(@RequestParam Integer oid) {
        return orderService.receive(oid);
    }
}
