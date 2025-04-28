package com.soulittude.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemDTO {
    private Long id;
    private ProductDTO product;
    private int quantity;
}