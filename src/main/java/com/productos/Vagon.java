package com.productos;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_vagon")
public class Vagon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_vagon")
    private String tipo;
    @Column(name = "cantidad_vagones")
    private int cantidad;
    private double peso;

    public Long getId() {
        return id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
}