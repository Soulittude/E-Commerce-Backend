package com.soulittude.e_commerce.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Better for most DBs
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true) // Ensure 1:1 relationship
    private UserEntity user;

    @Column(precision = 10, scale = 2) // Control decimal format
    private BigDecimal totalPrice = BigDecimal.ZERO; // Initialize

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void calculateTotal() {
        this.totalPrice = items.stream()
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
