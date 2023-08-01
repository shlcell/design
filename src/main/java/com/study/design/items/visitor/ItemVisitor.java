package com.study.design.items.visitor;

import com.study.design.items.node.ProductItem;

public interface ItemVisitor<T> {
    T visitor(ProductItem productItem);
}
