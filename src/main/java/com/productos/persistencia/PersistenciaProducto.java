package com.productos.persistencia;

import com.producto.Tren;
import com.producto.Locomotora;
import com.producto.Vagon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class PersistenciaProducto {

    public static void main(String[] args) {

        System.out.println("Inicio");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FerrocarrilPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Objetos
            Tren tren = new Tren();
            tren.setModelo("T-Express");
            tren.setFecha(LocalDate.now());
            tren.setPrecio(750.50);
            tren.setRentabilidad(18.5);

            Locomotora locomotora = new Locomotora();
            locomotora.setCategoria("Locomotora a Vapor"); 
            locomotora.setEnergia("Carbón");
            locomotora.setMantenimiento("Óptimo");
            locomotora.setVelocidad(200.0);

            Vagon vagon = new Vagon();
            vagon.setTipo("Vagon de Pasajeros");
            vagon.setCantidad(8); 
            vagon.setPeso(1500.0);

            locomotora.setTren(tren);
            locomotora.setVagon(vagon);

            tren.getLocomotoras().add(locomotora);


            em.persist(tren); 

            em.getTransaction().commit();

            System.out.println("DATOS INSERTADOS CORRECTAMENTE");

            // JPQL
            System.out.println("\n Lista de Trenes:");

            List<Tren> trenes = em.createQuery(
                    "SELECT t FROM Tren t", Tren.class
            ).getResultList();

            for (Tren t : trenes) {
                System.out.println("Modelo: " + t.getModelo() +
                        "Precio: " + t.getPrecio() +
                        "Rentabilidad: " + t.getRentabilidad());
            }

            System.out.println("\n️ Locomotoras a Vapor:");

            List<Locomotora> locomotoras = em.createQuery(
                    "SELECT l FROM Locomotora l WHERE l.categoria = :cat", Locomotora.class)
                    .setParameter("cat", "Eléctrica")
                    .getResultList();

            for (Locomotora l : locomotoras) {
                System.out.println("Tipo: " + l.getCategoria() +
                        "Velocidad: " + l.getVelocidad());
            }

            System.out.println("\n Vagones:");

            List<Vagon> vagones = em.createQuery(
                    "SELECT v FROM Vagon v WHERE v.tipo = :tipo", Vagon.class)
                    .setParameter("tipo", "Pasajeros")
                    .getResultList();

            for (Vagon v : vagones) {
                System.out.println("🧱 Tipo: " + v.getTipo() +
                        "Cantidad: " + v.getCantidad() +
                        "Peso: " + v.getPeso());
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // 🔒 cerrar recursos
            em.close();
            emf.close();
        }

        System.out.println("Finalización");
    }
}