package com.back.cookbook.business;

import com.back.cookbook.dataac.entity.ReceitaEntity;
import com.back.cookbook.dataac.impl.ReceitaDAOImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Responsável pela lógica das receitas
 */
@Service
public class ReceitaManager {
    @Autowired(required = true)
    public ReceitaDAOImpl receitaDAO;

    public void criarReceita(String nome, String modo_prep, String ingredientes, String tempo, String qtd_pessoas, String custo, String imagem) {
        ReceitaEntity receita = new ReceitaEntity(nome, modo_prep, ingredientes, tempo, qtd_pessoas, custo, imagem);
        receitaDAO.addReceita(receita);
    }    
    
    public ReceitaEntity obterReceitaPorId(Integer id) {
        return receitaDAO.getReceitaById(id);
    }
    
    public void atualizarReceita(Integer id, String nome, String modo_prep, String ingredientes, String tempo, String qtd_pessoas, String custo) {
        ReceitaEntity receita = receitaDAO.getReceitaById(id);
        if (receita != null) {
            receita.setNome(nome);
            receita.setModo_prep(modo_prep);
            receita.setIngredientes(ingredientes);
            receita.setTempo(tempo);
            receita.setQtdPessoas(qtd_pessoas);
            receita.setCusto(custo);
            receitaDAO.updateReceita(receita);
        }
    }
    
    public void deletarReceita(Integer id) {
        receitaDAO.deleteReceita(id);
    }
    
    public List<ReceitaEntity> listarReceitas() {
        return receitaDAO.listReceitas();
    }    
}
