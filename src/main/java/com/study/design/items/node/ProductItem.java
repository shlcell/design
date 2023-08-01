package com.study.design.items.node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductItem extends AbstractProductItem{
    private int id;
    private int pid;
    private String name;
    private List<ProductItem> child = new ArrayList<>();

    @Override
    public void removeChild(AbstractProductItem item) {
        ProductItem removeItem = (ProductItem) item;
        this.child = child.stream().filter(x->x.getId() != removeItem.getId()).collect(Collectors.toList());
    }

    @Override
    public void addChild(AbstractProductItem item) {
        this.child.add((ProductItem) item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductItem> getChild() {
        return child;
    }

    public void setChild(List<ProductItem> child) {
        this.child = child;
    }
}
