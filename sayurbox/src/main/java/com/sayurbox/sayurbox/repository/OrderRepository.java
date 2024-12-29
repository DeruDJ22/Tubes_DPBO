package com.sayurbox.sayurbox.repository;

import com.sayurbox.sayurbox.models.Order;
import com.sayurbox.sayurbox.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByCourierIsNull();
    List<Order> findByCourierIsNotNull();
    List<Order> findByUserUsername(String username);
    Optional<Order> findByOrderId(String orderId);
}
