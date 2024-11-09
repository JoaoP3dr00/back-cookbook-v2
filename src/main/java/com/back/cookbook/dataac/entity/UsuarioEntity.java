package com.back.cookbook.dataac.entity;

import jakarta.persistence.*;

@Entity(name = "Usuario")
@Table(name = "Usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;

    // Contrutores

    public UsuarioEntity(){

    }

    public UsuarioEntity(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // Getter

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    // Setter

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
