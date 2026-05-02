package com.example.productos;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_vagon")
public class Vagon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "id_locomotora")
    private Locomotora locomotora;

    private String tipo;
    private Integer cantidad;
    private Double peso;

    public Vagon() {}

    public String getId() { return id; }

    public Locomotora getLocomotora() { return locomotora; }

    public void setLocomotora(Locomotora locomotora) {
        this.locomotora = locomotora;
    }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() { return cantidad; }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPeso() { return peso; }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}