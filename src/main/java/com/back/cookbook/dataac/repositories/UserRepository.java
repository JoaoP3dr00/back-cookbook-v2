package com.back.cookbook.dataac.repositories;

import com.back.cookbook.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public UserEntity findByEmail(String email);

    public UserDetails findByLogin(String login);   // Spring Security

    public boolean existsByEmail(String email);
}
