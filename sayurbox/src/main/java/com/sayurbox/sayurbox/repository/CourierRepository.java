package com.sayurbox.sayurbox.repository;

import com.sayurbox.sayurbox.models.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {
    Courier findByKurirId(Long kurirId);
}
