package com.back.cookbook.domain;

import com.back.cookbook.domain.entity.ReceitaEntity;
import com.back.cookbook.domain.entity.UsuarioEntity;
//import com.back.cookbook.dataac.impl.ReceitaDAOImpl;
import com.back.cookbook.dataac.repositories.ReceitaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Responsável pela lógica das receitas
 */
@Service
public class ReceitaManager {
    @Autowired(required = true)
    public ReceitaRepository receitarepository;

    public void criarReceita(String nome, String modo_prep, String ingredientes, String tempo, String qtd_pessoas, String custo, String imagem, UsuarioEntity usuario) {
        ReceitaEntity receita = new ReceitaEntity(nome, modo_prep, ingredientes, tempo, qtd_pessoas, custo, imagem);
        receita.setUsuario(usuario);
        receitarepository.save(receita);
    }    
    
    public Optional<ReceitaEntity> obterReceitaPorId(Integer id) {
        return receitarepository.findById(id);
    }
    
    public void atualizarReceita(Integer id, String nome, String modo_prep, String ingredientes, String tempo, String qtd_pessoas, String custo, String imagem) {
        Optional<ReceitaEntity> receitaOptional = receitarepository.findById(id);
        ReceitaEntity receita = receitaOptional.orElseThrow(() -> new RuntimeException("Receita não encontrada"));
        
        if (receita != null) {
            if (nome != null) receita.setNome(nome);
            if (modo_prep != null) receita.setModo_prep(modo_prep);
            if (ingredientes != null) receita.setIngredientes(ingredientes);
            if (tempo != null) receita.setTempo(tempo);
            if (qtd_pessoas != null) receita.setQtdPessoas(qtd_pessoas);
            if (custo != null) receita.setCusto(custo);
            if (imagem != null) receita.setImagem(imagem);
            receitarepository.saveAndFlush(receita);
        }
    }
    
    public void deletarReceita(Integer id) {
        receitarepository.deleteById(id);
    }
    
    public List<ReceitaEntity> listarReceitas() {
        return receitarepository.findAll();
    }    
}
