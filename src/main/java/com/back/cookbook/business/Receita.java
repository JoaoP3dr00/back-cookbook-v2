package com.back.cookbook.business;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "Receita")
public class Receita {
    @Id
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "modo_prep")
    private String modo_prep;
    @Column(name = "ingredientes")
    private String ingredientes;

    public Receita(int id, String nome, String modo_prep, String ingredientes){
        this.id = id;
        this.nome = nome;
        this.modo_prep = modo_prep;
        this.ingredientes = ingredientes;
    }

    // Getter
    public int getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getModo_prep(){
        return this.modo_prep;
    }

    public String getIngredientes(){
        return this.ingredientes;
    }

    // Setter
    public void setId(int id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setModo_prep(String modo_p){
        this.modo_prep = modo_p;
    }

    public void setIngredientes(String ingred){
        this.ingredientes = ingred;
    }
}
