package com.back.cookbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.cookbook.dataac.repositories.UserRepository;
import com.back.cookbook.domain.DTO.AuthenticationDTO;
import com.back.cookbook.domain.DTO.LoginResponseDTO;
import com.back.cookbook.domain.DTO.RegisterDTO;
import com.back.cookbook.domain.entity.UserEntity;
import com.back.cookbook.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repo;

    @Autowired
    private TokenService token_service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = token_service.generateToken((UserEntity) auth.getPrincipal());  // Gera o token para o usuário

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repo.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity newUser = new UserEntity(data.login(), data.email(), encryptedPassword, data.role());

        this.repo.save(newUser);

        return ResponseEntity.ok().build();
    }
}
