package com.sayurbox.sayurbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sayurbox.sayurbox.models.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}
