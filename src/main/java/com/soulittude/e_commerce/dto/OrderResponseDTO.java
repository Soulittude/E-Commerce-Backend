package com.soulittude.e_commerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.soulittude.e_commerce.entity.OrderStatus;

import lombok.Data;

@Data
public class OrderResponseDTO {
    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private List<OrderItemDTO> items;
}