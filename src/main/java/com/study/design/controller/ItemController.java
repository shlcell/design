package com.study.design.controller;

import com.study.design.items.node.ProductItem;
import com.study.design.service.EsService;
import com.study.design.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("get")
    public ProductItem getItem() {
        return itemService.getItem();
    }

    @PostMapping("del")
    public ProductItem delItem(@RequestBody ProductItem productItem) {
        return itemService.delItem(productItem);
    }

    @PostMapping("add")
    public ProductItem addItem(@RequestBody ProductItem productItem) {
        return itemService.addItem(productItem);
    }

}
