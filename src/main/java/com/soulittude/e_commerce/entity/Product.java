package com.soulittude.e_commerce.entity;

import java.time.LocalDateTime;
import java.util.Locale.Category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    @Positive(message = "Price must be positive")
    private Double price;
    private String description;

    private String sku; // Unique product identifier
    private Integer stockQuantity; // Inventory tracking
    private String imageUrl; // Product image
    private LocalDateTime createdAt;

}
