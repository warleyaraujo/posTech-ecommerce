package com.postech.gestaodeenvio.repository;

import com.postech.gestaodeenvio.entities.orderentities.Orders;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {
}
