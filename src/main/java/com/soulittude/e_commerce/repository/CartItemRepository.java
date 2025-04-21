package com.soulittude.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soulittude.e_commerce.entity.Cart;
import com.soulittude.e_commerce.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCart(Cart cart);
}