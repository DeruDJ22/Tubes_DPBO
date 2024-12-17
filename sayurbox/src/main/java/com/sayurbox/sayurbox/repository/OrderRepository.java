package com.sayurbox.sayurbox.repository;

import com.sayurbox.sayurbox.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
