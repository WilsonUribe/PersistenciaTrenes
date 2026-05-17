package com.productos;
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

    @ManyToOne
    @JoinColumn(name = "ferrocarril_id")
    private Ferrocarril ferrocarril;
    @ManyToOne
    @JoinColumn(name = "cochera_id")
    private Cochera cochera;
    @OneToMany(mappedBy = "tren", cascade = CascadeType.ALL)
    private List<Locomotora> locomotoras = new ArrayList<>();

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
    public Ferrocarril getFerrocarril() {
        return ferrocarril;
    }
    public void setFerrocarril(Ferrocarril ferrocarril) {
        this.ferrocarril = ferrocarril;
    }
    public Cochera getCochera() {
        return cochera;
    }
    public void setCochera(Cochera cochera) {
        this.cochera = cochera;
    }
    public List<Locomotora> getLocomotoras() {
        return locomotoras;
    }
    public void setLocomotoras(List<Locomotora> locomotoras) {
        this.locomotoras = locomotoras;
    }
}