package com.soulittude.e_commerce.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soulittude.e_commerce.entity.Product;
import com.soulittude.e_commerce.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product createProduct(Product product) {
        // Simple validation
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        return productRepo.save(product);
    }
}