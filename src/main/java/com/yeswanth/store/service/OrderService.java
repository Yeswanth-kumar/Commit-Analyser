package com.yeswanth.store.service;

import com.yeswanth.store.model.Order;
import com.yeswanth.store.repository.OrderRepository;

public class OrderService {

    private final OrderRepository repository = new OrderRepository();

    public void placeOrder(String id, double amount) {

        Order order = new Order(id, amount);
        repository.save(order);
    }
}
