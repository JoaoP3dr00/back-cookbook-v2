package com.back.cookbook.dataac;

import org.springframework.stereotype.Service;

import com.back.cookbook.dataac.entity.ReceitaEntity;

/* 
 * Inteface DAO (Data Access Object) para realizar operações no banco 
 */
@Service
public interface ReceitaDAO {
    public abstract void addReceita(ReceitaEntity receita);
    //public abstract void deleteReceita(Integer receitaId);
    //public abstract void updateReceitaById(Integer receitaId);
}
