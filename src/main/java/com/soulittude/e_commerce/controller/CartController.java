package com.soulittude.e_commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soulittude.e_commerce.dto.CartRequestDTO;
import com.soulittude.e_commerce.entity.Cart;
import com.soulittude.e_commerce.entity.UserEntity;
import com.soulittude.e_commerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            @RequestBody CartRequestDTO request,
            @AuthenticationPrincipal UserEntity user
    ) {
        cartService.addToCart(request.getProductId(), request.getQuantity(), user);
        return ResponseEntity.ok().build();
    }
}
