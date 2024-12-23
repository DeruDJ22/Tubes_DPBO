package com.sayurbox.sayurbox.services;

import com.sayurbox.sayurbox.models.Courier;
import com.sayurbox.sayurbox.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourierService {

    @Autowired
    private CourierRepository courierRepository;

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public Optional<Courier> getCourierById(Long id) {
        return courierRepository.findById(id);
    }

    public Courier saveCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    public void deleteCourier(Long id) {
        courierRepository.deleteById(id);
    }
}
