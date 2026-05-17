package com.productos;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_cochera")
public class Cochera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int capacidad;
    private String ubicacion;
    private String estadoMantenimiento;

    @OneToMany(mappedBy = "cochera", cascade = CascadeType.ALL)
    private List<Tren> trenes = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public String getEstadoMantenimiento() {
        return estadoMantenimiento;
    }
    public void setEstadoMantenimiento(String estadoMantenimiento) {
        this.estadoMantenimiento = estadoMantenimiento;
    }
    public List<Tren> getTrenes() {
        return trenes;
    }
    public void setTrenes(List<Tren> trenes) {
        this.trenes = trenes;
    }
}