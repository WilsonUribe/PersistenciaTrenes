package com.example.productos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {

    private static final EntityManagerFactory emf;

    static {
        try {
            System.out.println("🔥 Iniciando conexión a PostgreSQL...");
            emf = Persistence.createEntityManagerFactory("FerrocarrilPU");
            System.out.println("✅ Conexión establecida correctamente");
        } catch (Exception e) {
            System.err.println("❌ ERROR DE CONEXIÓN:");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}