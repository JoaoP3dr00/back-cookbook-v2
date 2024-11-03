package com.back.cookbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.back.cookbook.business.Receita;
import com.back.cookbook.dataac.ReceitaDAO;

@Controller
@RequestMapping("/receita")
public class ReceitaController {
    public ReceitaDAO receitaDAO;
    
    @PostMapping("/adicionar")
    public void adicionar(@RequestParam Receita receita){
        receitaDAO.addReceita(receita);
    }
}
