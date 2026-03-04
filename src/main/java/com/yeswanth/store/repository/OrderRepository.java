package com.yeswanth.store.repository;

import com.yeswanth.store.model.Order;

public class OrderRepository {

    public void save(Order order) {
        System.out.println("Saving order: " + order.getOrderId());
    }
}
