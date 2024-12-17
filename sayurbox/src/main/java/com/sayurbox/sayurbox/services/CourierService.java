package com.sayurbox.sayurbox.services;

import com.sayurbox.sayurbox.models.Courier;
import com.sayurbox.sayurbox.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierService {

    @Autowired
    private CourierRepository courierRepository;

    public Courier getCourierById(Long kurirId) {
        return courierRepository.findByKurirId(kurirId);
    }
}
