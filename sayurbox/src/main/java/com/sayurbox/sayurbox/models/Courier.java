package com.sayurbox.sayurbox.models;

public class Courier {

    private int courierId;
    private String name;
    private int phoneNumber;

    public Courier(int courierId, String name, int phoneNumber) {
        this.courierId = courierId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void assignDelivery(int deliveryId) {
        System.out.println("Courier " + name + " (ID: " + courierId + ") has been assigned to delivery ID: " + deliveryId);
    }

    public void updateDeliveryStatus(int deliveryId, String status) {
        System.out.println("Courier " + name + " has updated the status of delivery ID " + deliveryId + " to: " + status);
    }

    public static void main(String[] args) {

        Courier courier = new Courier(1, "John Doe", 123456789);


        courier.assignDelivery(101);


        courier.updateDeliveryStatus(101, "In Transit");
    }
}
