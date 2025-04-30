package com.soulittude.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private Long productId;
    private String productName;
    private int quantity;
}