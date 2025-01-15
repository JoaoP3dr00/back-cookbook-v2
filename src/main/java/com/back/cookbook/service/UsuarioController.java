package com.back.cookbook.service;

import com.back.cookbook.service.DTO.LoginDTO;
import com.back.cookbook.domain.UsuarioManager;
import com.back.cookbook.domain.entity.UsuarioEntity;
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuário");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login){
        try{
            boolean sla = usuarioManager.validaUsuario(login.getEmail(), login.getSenha());
            if(!sla)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
            else
                return ResponseEntity.status(HttpStatus.OK).body("Login válido");
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar login");
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<UsuarioEntity> buscarUsuario(@RequestParam String email) {
        try {
            UsuarioEntity usuario = usuarioManager.findByEmail(email);
            if (usuario != null) {
                usuario.setReceitas(null); // Prevent sending receitas
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}