package com.study.design.controller;

import com.study.design.order.pojo.Order;
import com.study.design.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EsController {
    @Autowired
    private EsService esService;

    @PostMapping("es")
    public Boolean query(@RequestParam String query, Long fetchSize) {

        return esService.query(query, fetchSize);
    }

}
