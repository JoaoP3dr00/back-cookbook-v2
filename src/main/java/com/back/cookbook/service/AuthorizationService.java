package com.back.cookbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.back.cookbook.dataac.repositories.UserRepository;

// Service chamado automaticamente pelo Spring Security no login do usuário
@Service
public class AuthorizationService implements UserDetailsService{
    @Autowired
    UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByLogin(username);
    }
    
}
