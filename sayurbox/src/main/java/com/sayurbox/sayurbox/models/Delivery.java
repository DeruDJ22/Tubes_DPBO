package com.sayurbox.sayurbox.models;

public class Delivery {

    private int deliveryId;
    private int orderId;
    private String deliveryDate;
    private String status;

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
    public static void main(String[] args) {
        
        Delivery delivery = new Delivery(1, 101, "2024-12-20", "Pending");

        delivery.scheduleDelivery(101, "2024-12-22");
        System.out.println("Order ID: " + delivery.getOrderId());
        System.out.println("Delivery Date: " + delivery.getDeliveryDate());
        System.out.println("Status: " + delivery.getStatus());

        delivery.updateDeliveryStatus("Delivered");
        System.out.println("Updated Status: " + delivery.getStatus());
    }
}
