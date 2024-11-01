package com.back.cookbook.business;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "Receita")
@Table(name = "Receita")
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receita_sequence")
    @SequenceGenerator(sequenceName = "receita_sequence", name = "rec_seq") 
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

    public Receita(String nome, String modo_prep, String ingredientes, String tempo, String qtd_pessoas, String custo){
        this.nome = nome;
        this.modo_prep = modo_prep;
        this.ingredientes = ingredientes;
        this.qtd_pessoas = qtd_pessoas;
        this.tempo = tempo;
        this.custo = custo;
    }

    // Getter
    public Integer getId(){
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

    public String getTempo(){
        return this.tempo;
    }

    public String getQtdPessoas(){
        return this.qtd_pessoas;
    }

    public String getCusto(){
        return this.custo;
    }

    // Setter
    public void setId(Integer id){
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

    public void setTempo(String tempo){
        this.tempo = tempo;
    }

    public void setQtdPessoas(String qtd_pessoas){
        this.qtd_pessoas = qtd_pessoas;
    }

    public void setCusto(String custo){
        this.custo = custo;
    }
}
