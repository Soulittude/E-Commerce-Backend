package com.soulittude.e_commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soulittude.e_commerce.dto.CartRequestDTO;
import com.soulittude.e_commerce.entity.Cart;
import com.soulittude.e_commerce.entity.UserEntity;
import com.soulittude.e_commerce.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody CartRequestDTO request,
            @AuthenticationPrincipal UserDetails userDetails) {
        UserEntity user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        cartService.addToCart(request.productId(), request.quantity(), user);
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(cartService.getCart(user));
    }
}
