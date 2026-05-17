package com.proyecto.persistencia;
import com.productos.Cochera;
import com.productos.Ferrocarril;
import com.productos.Locomotora;
import com.productos.Tren;
import com.productos.Vagon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class ProductoPersistencia {

    private static EntityManager em;

    public static void main(String[] args) {

        System.out.println("INICIANDO APLICACION...");

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("FerrocarrilPU");

        em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            
            // Auxiliares
            Ferrocarril pesado =
                    crearFerrocarril(
                            "Ferrocarril Pesado",
                            "Carga Pesada"
                    );

            Ferrocarril ligero =
                    crearFerrocarril(
                            "Ferrocarril Ligero",
                            "Metro y Tranvia"
                    );

            Cochera cocheraCentral =
                    crearCochera(
                            "Cochera Central",
                            "Quito",
                            50,
                            "Activo"
                    );

            Cochera cocheraIndustrial =
                    crearCochera(
                            "Cochera Industrial",
                            "Pichincha",
                            80,
                            "Mantenimiento Pesado"
                    );

            // Base de Datos
            crearTren(
                    "Tren Imperial",
                    1200,
                    25,
                    "Vapor",
                    "Carbon",
                    "Pasajeros",
                    10,
                    pesado,
                    cocheraCentral
            );

            crearTren(
                    "Mineral Express",
                    2000,
                    35,
                    "Vapor",
                    "Carbon",
                    "Carga Minerales",
                    20,
                    pesado,
                    cocheraIndustrial
            );

            crearTren(
                    "Carga Auto",
                    1800,
                    30,
                    "Vapor",
                    "Carbon",
                    "Carga Vehiculos",
                    15,
                    pesado,
                    cocheraIndustrial
            );

            crearTren(
                    "Tren Petrolero",
                    2500,
                    40,
                    "Diesel",
                    "Combustible",
                    "Cisterna",
                    18,
                    pesado,
                    cocheraIndustrial
            );

            crearTren(
                    "Metro Quito",
                    900,
                    18,
                    "Electrica",
                    "Electricidad",
                    "Pasajeros",
                    12,
                    ligero,
                    cocheraCentral
            );

            crearTren(
                    "Tranvia Cuenca",
                    700,
                    15,
                    "Electrica",
                    "Electricidad",
                    "Tranvia",
                    6,
                    ligero,
                    cocheraCentral
            );

            crearTren(
                    "Tren Bala",
                    1600,
                    28,
                    "Electrica",
                    "Electricidad",
                    "Pasajeros",
                    14,
                    pesado,
                    cocheraCentral
            );

            crearTren(
                    "Tren Alimentario",
                    1700,
                    26,
                    "Electrica",
                    "Electricidad",
                    "Carga Alimentos",
                    16,
                    pesado,
                    cocheraIndustrial
            );

            em.getTransaction().commit();

            System.out.println("Datos de la base");

            // Consultas
            System.out.println("\nConsulta por precio:");

            List<Tren> trenesPrecio = em.createQuery(
                    "SELECT t FROM Tren t ORDER BY t.precio ASC",
                    Tren.class
            ).getResultList();

            for (Tren t : trenesPrecio) {

                System.out.println(
                        t.getModelo()
                        + "   Precio: $" + t.getPrecio()
                );
            }

            System.out.println("\nConsulta por cochera:");

            List<Cochera> cocheras = em.createQuery(
                    "SELECT c FROM Cochera c",
                    Cochera.class
            ).getResultList();

            for (Cochera c : cocheras) {

                System.out.println(
                        c.getNombre()
                        + "   Ubicacion: " + c.getUbicacion()
                        + "   Capacidad: " + c.getCapacidad()
                        + "   Estado: " + c.getEstadoMantenimiento()
                );
            }

            System.out.println("\nConsulta por tren ligero:");

            List<Tren> ligeros = em.createQuery(
                    "SELECT t FROM Tren t "
                    + "WHERE t.ferrocarril.nombre = :tipo",
                    Tren.class
            )
            .setParameter("tipo", "Ferrocarril Ligero")
            .getResultList();

            for (Tren t : ligeros) {

                System.out.println(
                        t.getModelo()
                        + "   Tipo: "
                        + t.getFerrocarril().getNombre()
                );
            }

            System.out.println("\nConsulta por locomotora a vapor:");

            List<Locomotora> locomotorasVapor = em.createQuery(
                    "SELECT l FROM Locomotora l "
                    + "WHERE l.categoria = :categoria",
                    Locomotora.class
            )
            .setParameter("categoria", "Vapor")
            .getResultList();

            for (Locomotora l : locomotorasVapor) {

                System.out.println(
                        l.getTren().getModelo()
                        + "   Energia: " + l.getEnergia()
                        + "   Locomotora a: " + l.getCategoria()
                );
            }

            System.out.println("\nConsulta por vagones de carga:");

            List<Vagon> vagonesCarga = em.createQuery(
                    "SELECT v FROM Vagon v "
                    + "WHERE v.tipo LIKE :tipo",
                    Vagon.class
            )
            .setParameter("tipo", "Carga%")
            .getResultList();

            for (Vagon v : vagonesCarga) {

                System.out.println(
                        v.getTipo()
                        + "   Cantidad de vagones: " + v.getCantidad()
                        + "   Peso total: " + v.getPeso()
                );
            }

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();
            emf.close();
        }

        System.out.println("\nFin");
    }

    // Objetos
    private static Ferrocarril crearFerrocarril(
            String nombre,
            String tipoVia
    ) {

        Ferrocarril ferrocarril = new Ferrocarril();

        ferrocarril.setNombre(nombre);
        ferrocarril.setTipoVia(tipoVia);

        em.persist(ferrocarril);

        return ferrocarril;
    }

    private static Cochera crearCochera(
            String nombre,
            String ubicacion,
            int capacidad,
            String estado
    ) {

        Cochera cochera = new Cochera();

        cochera.setNombre(nombre);
        cochera.setUbicacion(ubicacion);
        cochera.setCapacidad(capacidad);
        cochera.setEstadoMantenimiento(estado);

        em.persist(cochera);

        return cochera;
    }

    private static void crearTren(
            String modelo,
            double precio,
            double rentabilidad,
            String categoriaLocomotora,
            String energia,
            String tipoVagon,
            int cantidadVagones,
            Ferrocarril ferrocarril,
            Cochera cochera
    ) {

        Tren tren = new Tren();

        tren.setModelo(modelo);
        tren.setFecha(LocalDate.now());
        tren.setPrecio(precio);
        tren.setRentabilidad(rentabilidad);
        tren.setFerrocarril(ferrocarril);
        tren.setCochera(cochera);

        Vagon vagon = new Vagon();

        vagon.setTipo(tipoVagon);
        vagon.setCantidad(cantidadVagones);
        vagon.setPeso(1500);

        Locomotora locomotora = new Locomotora();

        locomotora.setCategoria(categoriaLocomotora);
        locomotora.setEnergia(energia);
        locomotora.setMantenimiento("Activo");
        locomotora.setVelocidad(180);

        locomotora.setTren(tren);
        locomotora.setVagon(vagon);

        tren.getLocomotoras().add(locomotora);

        em.persist(tren);
        em.persist(vagon);
        em.persist(locomotora);
    }
}