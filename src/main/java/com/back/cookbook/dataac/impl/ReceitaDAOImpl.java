package com.back.cookbook.dataac.impl;

import com.back.cookbook.dataac.connection.ConnectionFactory;
import org.springframework.stereotype.Service;
import com.back.cookbook.dataac.entity.ReceitaEntity;
import com.back.cookbook.dataac.ReceitaDAO;
import jakarta.persistence.EntityManager;

/*
 * Classe de implementação da interface ReceitaDAO
 */

/**
 * MUDAR ISSO PRA UM REPOSITORY
 */
@Service
public class ReceitaDAOImpl implements ReceitaDAO{
    EntityManager em = ConnectionFactory.getConnection();

    @Override   
    public void addReceita(ReceitaEntity receita){
        try {
            em.getTransaction().begin();
            em.persist(receita);
            System.out.println("SALVOU RECEITA ----------------------------------------------");
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }


}