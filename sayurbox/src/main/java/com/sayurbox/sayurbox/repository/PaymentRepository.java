package com.sayurbox.sayurbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sayurbox.sayurbox.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
