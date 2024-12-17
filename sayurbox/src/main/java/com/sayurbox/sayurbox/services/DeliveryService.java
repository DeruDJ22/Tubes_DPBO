package com.sayurbox.sayurbox.services;

import com.sayurbox.sayurbox.models.Delivery;
import com.sayurbox.sayurbox.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public Optional<Delivery> getDeliveryById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId);
    }
}
