package com.soulittude.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
}