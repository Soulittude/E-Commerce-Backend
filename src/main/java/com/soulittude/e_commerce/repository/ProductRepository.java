package com.soulittude.e_commerce.repository;

import com.soulittude.e_commerce.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) OR :name IS NULL) AND " +
            "(p.price >= :minPrice OR :minPrice IS NULL) AND " +
            "(p.price <= :maxPrice OR :maxPrice IS NULL)")
    Page<Product> searchProducts(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable);
}