package com.example.productos;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tren")
public class Tren {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tren")
    private Long id;

    private String modelo;
    private LocalDate fecha;
    private double precio;
    private double rentabilidad;

    @ManyToOne
    @JoinColumn(name = "id_ferrocarril")
    private Persistencia ferrocarril;

    @OneToMany(mappedBy = "tren", cascade = CascadeType.ALL)
    private List<Locomotora> locomotoras = new ArrayList<>();

    public Tren() {}

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setRentabilidad(double rentabilidad) {
        this.rentabilidad = rentabilidad;
    }

    public void setFerrocarril(Persistencia ferrocarril) {
        this.ferrocarril = ferrocarril;
    }

    public List<Locomotora> getLocomotoras() {
        return locomotoras;
    }
}