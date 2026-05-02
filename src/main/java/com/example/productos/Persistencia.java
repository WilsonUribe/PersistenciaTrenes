package com.example.productos;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_ferrocarril")
public class Persistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @OneToMany(mappedBy = "ferrocarril", cascade = CascadeType.ALL)
    private List<Tren> trenes = new ArrayList<>();

    public Persistencia() {}

    public Persistencia(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Tren> getTrenes() {
        return trenes;
    }

    public void setTrenes(List<Tren> trenes) {
        this.trenes = trenes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static void main(String[] args) {

        System.out.println("Iniciando");

        EntityManager em = Util.getEntityManager();

        em.getTransaction().begin();

        Persistencia f = new Persistencia("Ferrocarril");

        Tren t = new Tren();
        t.setModelo("T-1000");
        t.setFecha(LocalDate.now());
        t.setPrecio(500.45);
        t.setRentabilidad(15.2);
        t.setFerrocarril(f);

        Locomotora l = new Locomotora();
        l.setCategoria("Locomotora a Diesel");
        l.setEnergia("Diesel");
        l.setMantenimiento("OK");
        l.setVelocidad(120.0);
        l.setTren(t);

        Vagon v = new Vagon();
        v.setTipo("Vagon de Carga");
        v.setCantidad(10);
        v.setPeso(2000.0);
        v.setLocomotora(l);

        // Relaciones
        l.setVagon(v);
        t.getLocomotoras().add(l);
        f.getTrenes().add(t); 

        em.persist(f);

        em.getTransaction().commit();
        em.close();

        System.out.println("Datos Completados");
    }
}