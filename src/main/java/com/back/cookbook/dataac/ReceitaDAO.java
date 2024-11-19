package com.back.cookbook.dataac;

import java.util.List;

import org.springframework.stereotype.Service;

import com.back.cookbook.dataac.entity.ReceitaEntity;

/* 
 * Inteface DAO (Data Access Object) para realizar operações no banco 
 */
@Service
public interface ReceitaDAO {
    void addReceita(ReceitaEntity receita);
    ReceitaEntity getReceitaById(Integer receitaId);
    void updateReceita(ReceitaEntity receita);
    void deleteReceita(Integer receitaId);
    List<ReceitaEntity> listReceitas(); // Para listar todas as receitas
}
