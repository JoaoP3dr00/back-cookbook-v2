// package com.back.cookbook.dataac.impl;

// import com.back.cookbook.dataac.connection.ConnectionFactory;

// import java.util.List;

// import org.springframework.stereotype.Service;
// import com.back.cookbook.domain.entity.ReceitaEntity;
// import com.back.cookbook.dataac.ReceitaDAO;
// import jakarta.persistence.EntityManager;

// /*
//  * Classe de implementação da interface ReceitaDAO
//  */

// /**
//  * MUDAR ISSO PRA UM REPOSITORY
//  */
// @Service
// public class ReceitaDAOImpl implements ReceitaDAO{
//     EntityManager em = ConnectionFactory.getConnection();

//     @Override
//     public void addReceita(ReceitaEntity receita) {
//         try {
//             em.getTransaction().begin();
//             em.persist(receita);
//             em.getTransaction().commit();
//         } catch (Exception e) {
//             em.getTransaction().rollback();
//             throw new RuntimeException("Erro ao salvar a receita no banco de dados: " + e.getMessage(), e);
//         }
//     }


//     @Override
//     public ReceitaEntity getReceitaById(Integer receitaId) {
//         return em.find(ReceitaEntity.class, receitaId);
//     }

//     @Override
//     public void updateReceita(ReceitaEntity receita) {
//         try {
//             em.getTransaction().begin();
//             em.merge(receita);
//             em.getTransaction().commit();
//         } catch (Exception e) {
//             em.getTransaction().rollback();
//         }
//     }

//     @Override
//     public void deleteReceita(Integer receitaId) {
//         try {
//             em.getTransaction().begin();
//             ReceitaEntity receita = em.find(ReceitaEntity.class, receitaId);
//             if (receita != null) {
//                 em.remove(receita);
//             }
//             em.getTransaction().commit();
//         } catch (Exception e) {
//             em.getTransaction().rollback();
//         }
//     }

//     @Override
//     public List<ReceitaEntity> listReceitas() {
//         return em.createQuery("SELECT r FROM ReceitaEntity r", ReceitaEntity.class).getResultList();
//     }

// }