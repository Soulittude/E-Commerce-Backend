package com.soulittude.e_commerce.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private LocalDateTime orderDate;
    private String status; // e.g., "PENDING", "COMPLETED"
}