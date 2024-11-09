package com.back.cookbook.service;

import com.back.cookbook.business.UsuarioManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired(required = true)
    UsuarioManager usuarioManager;

    // Retornar um erro ou código de erro se der errado
    @PostMapping("/adicionar")
    public void adicionar(@RequestParam String email, @RequestParam String senha){
        try {
            usuarioManager.criarUsuario(email, senha);
        } catch(Exception e){
            System.out.println(e);
        }
    }
}