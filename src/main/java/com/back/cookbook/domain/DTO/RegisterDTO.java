package com.back.cookbook.domain.DTO;

import com.back.cookbook.domain.entity.UserRole;

public record RegisterDTO(String login, String password, String email, UserRole role) {
    
}
