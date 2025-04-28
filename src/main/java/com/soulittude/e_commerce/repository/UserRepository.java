package com.soulittude.e_commerce.repository;

import com.soulittude.e_commerce.entity.UserEntity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @EntityGraph(attributePaths = {"cart"})
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}