package com.soulittude.e_commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soulittude.e_commerce.dto.CartRequestDTO;
import com.soulittude.e_commerce.dto.CartResponseDTO;
import com.soulittude.e_commerce.entity.UserEntity;
import com.soulittude.e_commerce.repository.UserRepository;
import com.soulittude.e_commerce.service.CartService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addToCart(
            @RequestBody CartRequestDTO request,
            @AuthenticationPrincipal UserDetails userDetails) {
        
        UserEntity user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        CartResponseDTO cartResponse = cartService.addToCart(
                request.getProductId(), 
                request.getQuantity(), 
                user
        );
        
        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping
    public ResponseEntity<CartResponseDTO> getCart(
            @AuthenticationPrincipal UserDetails userDetails) {
        
        UserEntity user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return ResponseEntity.ok(cartService.getCartDTO(user));
    }
}