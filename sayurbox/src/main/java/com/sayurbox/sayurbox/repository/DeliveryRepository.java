package com.sayurbox.sayurbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sayurbox.sayurbox.models.Product;

public interface DeliveryRepository extends JpaRepository<Product, Integer>{
    
}
