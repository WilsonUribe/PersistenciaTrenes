package com.producto;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tren")
public class Tren {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    private LocalDate fecha;

    private double precio;

    private double rentabilidad;

    @OneToMany(mappedBy = "tren", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Locomotora> locomotoras = new ArrayList<>();

    public Tren() {}

    public Tren(String modelo, LocalDate fecha, double precio, double rentabilidad) {
        this.modelo = modelo;
        this.fecha = fecha;
        this.precio = precio;
        this.rentabilidad = rentabilidad;
    }

    public void addLocomotora(Locomotora loco) {
        locomotoras.add(loco);
        loco.setTren(this);
    }

    public void removeLocomotora(Locomotora loco) {
        locomotoras.remove(loco);
        loco.setTren(null);
    }

    public Long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getRentabilidad() {
        return rentabilidad;
    }

    public void setRentabilidad(double rentabilidad) {
        this.rentabilidad = rentabilidad;
    }

    public List<Locomotora> getLocomotoras() {
        return locomotoras;
    }

    public void setLocomotoras(List<Locomotora> locomotoras) {
        this.locomotoras = locomotoras;
    }
}