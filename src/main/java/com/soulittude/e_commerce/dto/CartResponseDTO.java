package com.soulittude.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class CartResponseDTO {
    private Long id;
    private BigDecimal totalPrice;
    private List<CartItemDTO> items;
}