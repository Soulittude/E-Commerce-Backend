package com.soulittude.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soulittude.e_commerce.entity.OrderEntity;
import com.soulittude.e_commerce.entity.UserEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUser(UserEntity user);
}