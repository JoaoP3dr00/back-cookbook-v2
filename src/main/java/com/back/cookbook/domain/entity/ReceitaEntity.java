package com.back.cookbook.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "Receita")
@Table(name = "Receita")
public class ReceitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "modo_prep")
    private String modo_prep;

    @Column(name = "ingredientes")
    private String ingredientes;

    @Column(name = "tempo")
    private String tempo;

    @Column(name = "qtd_pessoas")
    private String qtd_pessoas;

    @Column(name = "custo")
    private String custo;

    @Column(name = "imagem", nullable = true)
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private UserEntity usuario;

    // Construtor com todos os parâmetros
    public ReceitaEntity(String nome, String modo_prep, String ingredientes, String tempo, String qtd_pessoas, String custo, String imagem) {
        this.nome = nome;
        this.modo_prep = modo_prep;
        this.ingredientes = ingredientes;
        this.qtd_pessoas = qtd_pessoas;
        this.tempo = tempo;
        this.custo = custo;
        this.imagem = imagem;
    }

    public ReceitaEntity() {
        // Construtor sem parâmetros necessário para o JPA
    }

    // Getters
    public Integer getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getModo_prep() {
        return this.modo_prep;
    }

    public String getIngredientes() {
        return this.ingredientes;
    }

    public String getTempo() {
        return this.tempo;
    }

    public String getQtdPessoas() {
        return this.qtd_pessoas;
    }

    public String getCusto() {
        return this.custo;
    }

    public String getImagem() {
        return this.imagem;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setModo_prep(String modo_p) {
        this.modo_prep = modo_p;
    }

    public void setIngredientes(String ingred) {
        this.ingredientes = ingred;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public void setQtdPessoas(String qtd_pessoas) {
        this.qtd_pessoas = qtd_pessoas;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
