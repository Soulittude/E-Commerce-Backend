package com.soulittude.e_commerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soulittude.e_commerce.dto.OrderResponseDTO;
import com.soulittude.e_commerce.entity.UserEntity;
import com.soulittude.e_commerce.repository.UserRepository;
import com.soulittude.e_commerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(
            @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ResponseEntity.ok(orderService.placeOrder(user));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getOrders(
            @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ResponseEntity.ok(orderService.getUserOrders(user));
    }
}