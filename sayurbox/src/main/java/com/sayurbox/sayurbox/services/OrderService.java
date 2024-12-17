package com.sayurbox.sayurbox.services;

import com.sayurbox.sayurbox.models.Order;
import com.sayurbox.sayurbox.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service    
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Integer totalItems, Double totalPrice) {
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8);
        Order order = new Order(orderId, totalItems, totalPrice);
        return orderRepository.save(order);
    }
}
