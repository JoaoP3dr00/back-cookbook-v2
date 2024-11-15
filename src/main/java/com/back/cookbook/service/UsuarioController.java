package com.back.cookbook.service;

import com.back.cookbook.business.DTO.LoginDTO;
import com.back.cookbook.business.UsuarioManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired(required = true)
    UsuarioManager usuarioManager;

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@RequestParam String email, @RequestParam String senha){
        try {
            boolean sla = usuarioManager.criarUsuario(email, senha);
            if(!sla)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email inválido");
            else
                return ResponseEntity.status(HttpStatus.OK).body("Usuario criado");
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login){
        try{
            boolean sla = usuarioManager.validaUsuario(login.getEmail(), login.getSenha());
            if(!sla)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ou senha inválidos");
            else
                return ResponseEntity.status(HttpStatus.OK).body("Login válido");
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}