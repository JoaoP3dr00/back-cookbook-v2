package com.back.cookbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.back.cookbook.business.Receita;
import com.back.cookbook.dataac.ReceitaDAO;
import com.back.cookbook.dataac.impl.ReceitaDAOImpl;

@RestController
@RequestMapping("/receita")
public class ReceitaController {
    public ReceitaDAOImpl receitaDAO = new ReceitaDAOImpl();
    
    @GetMapping("/")
    @ResponseBody
    public String getReceitas(){
        return "oi";
    }

    @PostMapping("/adicionar")
    public void adicionar(@RequestParam String nome,@RequestParam String modo_prep, @RequestParam String ingredientes, @RequestParam String tempo, @RequestParam String qtd_pessoas, @RequestParam String custo){
        Receita receita = new Receita(nome, nome, nome, nome, nome, nome);
        
        receitaDAO.addReceita(receita);
    }
}
