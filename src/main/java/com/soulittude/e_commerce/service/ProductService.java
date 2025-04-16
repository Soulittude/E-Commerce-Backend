package com.soulittude.e_commerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soulittude.e_commerce.entity.Product;
import com.soulittude.e_commerce.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    public Product createProduct(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        return productRepo.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = productRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
        
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        
        return productRepo.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
        productRepo.delete(product);
    }
}