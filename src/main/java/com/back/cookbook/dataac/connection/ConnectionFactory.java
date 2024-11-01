package com.back.cookbook.dataac.connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionFactory {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("cookbook");

    public static EntityManager getConnection() {
        return emf.createEntityManager();
    }
}
