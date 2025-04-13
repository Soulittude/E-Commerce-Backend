package com.soulittude.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Bidirectional relationship (optional)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}