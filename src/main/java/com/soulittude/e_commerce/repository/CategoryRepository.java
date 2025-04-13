package com.soulittude.e_commerce.repository;

import com.soulittude.e_commerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}