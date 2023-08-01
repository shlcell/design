package com.study.design.items.visitor;

import com.study.design.MockDb;
import com.study.design.items.node.ProductItem;
import org.springframework.stereotype.Component;

@Component
public class AddItemVisitor implements ItemVisitor<ProductItem>{
    // 入参是 id 2, pid为 1
    @Override
    public ProductItem visitor(ProductItem productItem) {
        ProductItem currentItem = MockDb.ProductItem; //  从缓存来的 to do
        if(productItem.getId() == currentItem.getId()) {
            throw new UnsupportedOperationException("根节点是唯一的。");
        }
        if(productItem.getPid() == currentItem.getId()) {
            currentItem.addChild(productItem);
            return currentItem;
        }
        addChild(productItem, currentItem);
        return currentItem;
    }

    private void addChild(ProductItem productItem, ProductItem currentItem) {
        for(ProductItem item : currentItem.getChild()) {
            if(item.getId() == productItem.getPid()) {
                item.addChild(productItem);
                break;
            } else {
                addChild(productItem, item);
            }
        }
    }
}
