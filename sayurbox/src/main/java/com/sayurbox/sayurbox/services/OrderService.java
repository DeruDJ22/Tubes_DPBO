package com.sayurbox.sayurbox.services;

import com.sayurbox.sayurbox.models.Courier;
import com.sayurbox.sayurbox.models.Order;
import com.sayurbox.sayurbox.models.User;
import com.sayurbox.sayurbox.repository.CourierRepository;
import com.sayurbox.sayurbox.repository.OrderRepository;
import com.sayurbox.sayurbox.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourierRepository courierRepository;

    public Order createOrder(Integer totalItems, Double totalPrice, User user) {
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8);
        Order order = new Order(orderId, totalItems, totalPrice, user);
        order.setPaymentStatus("BELUM_BAYAR");
        return orderRepository.save(order);
    }
    

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> getOrdersByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return orderRepository.findByUser(user);
        }
        return List.of();
    }

    public List<Order> getUnassignedOrders() {
        return orderRepository.findByCourierIsNull();
    }

    public void assignCourierToOrder(Long orderId, Long courierId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        Courier courier = courierRepository.findById(courierId).orElseThrow(() -> new IllegalArgumentException("Courier not found"));
        
        order.setCourier(courier);
        orderRepository.save(order);
    }

    public List<Order> getAssignedOrders() {
        return orderRepository.findByCourierIsNotNull();
    }
    
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
    
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(String orderId) {
        return orderRepository.findByOrderId(orderId).orElse(null);
    }
    
}
