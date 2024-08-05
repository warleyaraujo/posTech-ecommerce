package com.postech.gestaodeenvio.repository;

import com.postech.gestaodeenvio.entities.orderentities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
