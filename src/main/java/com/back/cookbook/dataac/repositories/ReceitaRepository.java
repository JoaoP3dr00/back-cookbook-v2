package com.back.cookbook.dataac.repositories;

import com.back.cookbook.domain.entity.ReceitaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<ReceitaEntity, Integer> {
    public Optional<ReceitaEntity> findById(Integer id);
}
