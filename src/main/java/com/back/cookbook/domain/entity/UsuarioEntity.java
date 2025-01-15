package com.back.cookbook.domain.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Usuario")
@Table(name = "Usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ReceitaEntity> receitas;

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

    public List<ReceitaEntity> getReceitas() {
        return receitas;
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

    public void setReceitas(List<ReceitaEntity> receitas) {
        this.receitas = receitas;
    }
}
