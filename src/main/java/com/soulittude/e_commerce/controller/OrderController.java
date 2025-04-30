package com.soulittude.e_commerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soulittude.e_commerce.dto.OrderResponseDTO;
import com.soulittude.e_commerce.entity.OrderEntity;
import com.soulittude.e_commerce.entity.UserEntity;
import com.soulittude.e_commerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(
            @AuthenticationPrincipal UserEntity user) {
        OrderResponseDTO orderDTO = orderService.placeOrder(user);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getOrders(
            @AuthenticationPrincipal UserEntity user) {
        List<OrderResponseDTO> orders = orderService.getUserOrders(user);
        return ResponseEntity.ok(orders);
    }

}