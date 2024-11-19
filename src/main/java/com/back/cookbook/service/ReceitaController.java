package com.back.cookbook.service;

import com.back.cookbook.business.ReceitaManager;
import com.back.cookbook.dataac.entity.ReceitaEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/receita")
public class ReceitaController {
    @Autowired(required = true)
    ReceitaManager receitaManager;

    @GetMapping("/")
    @ResponseBody
    public String getReceitas(){
        return "oi";
    }

    // Retornar um erro ou código de erro se der errado
    @PostMapping("/adicionar")
    public void adicionar(@RequestParam String nome, @RequestParam String modo_prep, @RequestParam String ingredientes, @RequestParam String tempo, @RequestParam String qtd_pessoas, @RequestParam String custo){
        try {
            receitaManager.criarReceita(nome, modo_prep, ingredientes, tempo, qtd_pessoas, custo);
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ReceitaEntity obterReceita(@RequestParam Integer id) {
        return receitaManager.obterReceitaPorId(id);
    }

    @PutMapping("/atualizar")
    public void atualizarReceita(
        @RequestParam Integer id, 
        @RequestParam String nome, 
        @RequestParam String modo_prep, 
        @RequestParam String ingredientes, 
        @RequestParam String tempo, 
        @RequestParam String qtd_pessoas, 
        @RequestParam String custo
    ) {
        receitaManager.atualizarReceita(id, nome, modo_prep, ingredientes, tempo, qtd_pessoas, custo);
    }

    @DeleteMapping("/deletar")
    public void deletarReceita(@RequestParam Integer id) {
        receitaManager.deletarReceita(id);
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<ReceitaEntity> listarReceitas() {
        return receitaManager.listarReceitas();
    }

}
