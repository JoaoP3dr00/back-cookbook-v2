package com.back.cookbook.domain.entity;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import com.back.cookbook.domain.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Usuario")
@Table(name = "Usuario")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "role")
    private UserRole role;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ReceitaEntity> receitas;

    // Contrutores

    public UserEntity(){

    }

    public UserEntity(String login, String email, String senha, UserRole role) {
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    // Getter

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setReceitas(List<ReceitaEntity> receitas) {
        this.receitas = receitas;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER")); // Quando a role for ADMIN, o usuário terá as permissões de admin e usuário normal
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
