package com.postech.gestaodeenvio.repository;

import com.postech.gestaodeenvio.entities.orderentities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
