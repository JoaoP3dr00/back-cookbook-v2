package com.back.cookbook.dataac.impl;

import com.back.cookbook.dataac.connection.ConnectionFactory;
import com.back.cookbook.business.Receita;
import com.back.cookbook.dataac.ReceitaDAO;
import jakarta.persistence.EntityManager;

/*
 * Classe de implementação da interface ReceitaDAO
 */
public class ReceitaDAOImpl implements ReceitaDAO{
    EntityManager em = ConnectionFactory.getConnection();

    @Override
    public void addReceita(Receita receita){
        try {
            em.getTransaction().begin();
            if (receita.getId() == null) {
                em.persist(receita);
                System.out.println("SALVOU USUARIO ----------------------------------------------");
            } else {
                em.merge(receita);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}