package com.back.cookbook.business;

import com.back.cookbook.dataac.entity.ReceitaEntity;
import com.back.cookbook.dataac.entity.UsuarioEntity;
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

    public void criarReceita(String nome, String modo_prep, String ingredientes, String tempo, String qtd_pessoas, String custo, String imagem, UsuarioEntity usuario) {
        ReceitaEntity receita = new ReceitaEntity(nome, modo_prep, ingredientes, tempo, qtd_pessoas, custo, imagem);
        receita.setUsuario(usuario);
        receitaDAO.addReceita(receita);
    }    
    
    public ReceitaEntity obterReceitaPorId(Integer id) {
        return receitaDAO.getReceitaById(id);
    }
    
    public void atualizarReceita(Integer id, String nome, String modo_prep, String ingredientes, String tempo, String qtd_pessoas, String custo, String imagem) {
        ReceitaEntity receita = receitaDAO.getReceitaById(id);
        if (receita != null) {
            if (nome != null) receita.setNome(nome);
            if (modo_prep != null) receita.setModo_prep(modo_prep);
            if (ingredientes != null) receita.setIngredientes(ingredientes);
            if (tempo != null) receita.setTempo(tempo);
            if (qtd_pessoas != null) receita.setQtdPessoas(qtd_pessoas);
            if (custo != null) receita.setCusto(custo);
            if (imagem != null) receita.setImagem(imagem);
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
