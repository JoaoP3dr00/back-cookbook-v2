package com.back.cookbook.dataac.repositories;

import com.back.cookbook.dataac.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsuarioEntity, Integer> {
    public UsuarioEntity findByEmail(String email);

    public boolean existsByEmail(String email);
}
