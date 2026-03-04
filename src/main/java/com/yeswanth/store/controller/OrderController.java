package com.yeswanth.store.controller;

import com.yeswanth.store.service.OrderService;

public class OrderController {

    private final OrderService service = new OrderService();

    public void createOrder() {
        service.placeOrder("ORD-1001", 120.0);
    }
}
