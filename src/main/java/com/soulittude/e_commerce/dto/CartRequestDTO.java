package com.soulittude.e_commerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartRequestDTO {
    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;
}