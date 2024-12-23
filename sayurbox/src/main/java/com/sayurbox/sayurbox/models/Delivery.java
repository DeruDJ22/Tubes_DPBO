package com.sayurbox.sayurbox.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryId;

    private int orderId;
    private String deliveryDate;
    private String status;

    public Delivery() {
        // Default constructor
    }

    public Delivery(int deliveryId, int orderId, String deliveryDate, String status) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void scheduleDelivery(int orderId, String deliveryDate) {
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.status = "Scheduled";
        System.out.println("Delivery scheduled successfully.");
    }

    public void updateDeliveryStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Delivery status updated to: " + newStatus);
    }
}
