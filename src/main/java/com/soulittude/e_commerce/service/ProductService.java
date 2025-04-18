package com.soulittude.e_commerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soulittude.e_commerce.entity.Product;
import com.soulittude.e_commerce.exception.ProductNotFoundException;
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
                .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());

        return productRepo.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepo.delete(product);
    }

    public Page<Product> searchProducts(String name, Double minPrice, Double maxPrice, Pageable pageable) {
        return productRepo.searchProducts(name, minPrice, maxPrice, pageable);
    }
}