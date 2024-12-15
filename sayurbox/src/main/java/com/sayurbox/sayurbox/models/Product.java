package com.sayurbox.sayurbox.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    private int productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String gambar;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private int stock;

    public Product() {}
    public Product(int productId, String name, String description, float price, int stock, String gambar) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.gambar = gambar;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Additional Methods
    public void updateStock(int newStock) {
        this.stock = newStock;
        System.out.println("Stock updated to: " + newStock);
    }

    public void updatePrice(float newPrice) {
        this.price = newPrice;
        System.out.println("Price updated to: " + newPrice);
    }

    
}

