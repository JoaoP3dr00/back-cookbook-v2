package com.back.cookbook.business;

import com.back.cookbook.dataac.entity.ReceitaEntity;
import com.back.cookbook.dataac.impl.ReceitaDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Responsável pela lógica das receitas
 */
@Service
public class ReceitaManager {
    @Autowired(required = true)
    public ReceitaDAOImpl receitaDAO;

    public void criarReceita(String nome, String modo_prep, String ingredientes, String tempo, String qtd_pessoas, String custo){
        ReceitaEntity receita = new ReceitaEntity(nome, modo_prep, ingredientes, tempo, qtd_pessoas, custo);

        receitaDAO.addReceita(receita);
    }

    // resto do crud
}
