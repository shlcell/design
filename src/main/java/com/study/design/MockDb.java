package com.study.design;

import com.study.design.items.node.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class MockDb {
    public static ProductItem ProductItem = new ProductItem();
    static {
        ProductItem.setId(1);
        ProductItem.setPid(0);
        ProductItem.setName("书籍");
        List<ProductItem> child = new ArrayList<>();
        ProductItem c1 = new ProductItem();
        c1.setId(2);
        c1.setPid(1);
        c1.setName("技术书籍");
        ProductItem c2 = new ProductItem();
        c2.setId(3);
        c2.setPid(1);
        c2.setName("历史书籍");
        List<ProductItem> child1 = new ArrayList<>();
        ProductItem c3 = new ProductItem();
        c3.setId(4);
        c3.setPid(2);
        c3.setName("并发编程");
        ProductItem c4 = new ProductItem();
        c4.setId(5);
        c4.setPid(2);
        c4.setName("JVM");
        child1.add(c3);
        child1.add(c4);
        c1.setChild(child1);
        child.add(c1);
        child.add(c2);
        ProductItem.setChild(child);
    }
}
