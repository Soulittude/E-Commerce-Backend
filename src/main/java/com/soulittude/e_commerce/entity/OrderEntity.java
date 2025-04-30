package com.soulittude.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Table(name = "orders", indexes = @Index(columnList = "user_id, status"))
@Entity
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> items;

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @PrePersist
    protected void onCreate() {
        orderDate = LocalDateTime.now();
    }

}