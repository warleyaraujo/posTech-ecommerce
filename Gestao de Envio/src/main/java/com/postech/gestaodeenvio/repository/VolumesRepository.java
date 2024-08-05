package com.postech.gestaodeenvio.repository;

import com.postech.gestaodeenvio.entities.orderentities.Volumes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolumesRepository extends JpaRepository<Volumes, Long> {
}
