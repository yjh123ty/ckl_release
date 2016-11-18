/**
 * Project Name:ckl
 * File Name:AddOrderDetailList.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.util.List;

/**
 * <p>Title:AddOrderDetailList</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月13日下午5:24:44</p>
 * <p>Description:订单中追加的商品项</p>
 */
public class AddOrderDetailList {
    
    private List<OrderServiceDetail> addLists;

    public List<OrderServiceDetail> getAddLists() {
        return addLists;
    }

    public void setAddLists(List<OrderServiceDetail> addLists) {
        this.addLists = addLists;
    }

    @Override
    public String toString() {
        return "AddOrderDetailList [addLists=" + addLists + "]";
    }
    
    
}
