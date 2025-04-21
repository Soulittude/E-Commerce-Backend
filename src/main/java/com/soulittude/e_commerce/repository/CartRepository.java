package com.soulittude.e_commerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soulittude.e_commerce.entity.Cart;
import com.soulittude.e_commerce.entity.UserEntity;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(UserEntity user);
}