package com.back.cookbook.dataac;

import java.beans.JavaBean;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.back.cookbook.business.Receita;

/* 
 * Inteface DAO (Data Access Object) para realizar operações no banco 
 */
@Service
public interface ReceitaDAO {
    public abstract void addReceita(Receita receita);
    //public abstract void deleteReceita(Integer receitaId);
    //public abstract void updateReceitaById(Integer receitaId);
}
