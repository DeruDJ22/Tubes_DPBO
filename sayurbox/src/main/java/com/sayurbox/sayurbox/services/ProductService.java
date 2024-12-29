package com.sayurbox.sayurbox.services;

import com.sayurbox.sayurbox.abstact.ProductAbstact;
import com.sayurbox.sayurbox.models.Product;
import com.sayurbox.sayurbox.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sayurbox.sayurbox.repository.UserRepo;

import java.util.List;

@Service
public class ProductService extends ProductAbstact{

    @Autowired
    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll(); // Mengambil semua data dari tabel
    }
}

