package com.example.productos;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_locomotora")
public class Locomotora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tren")
    private Tren tren;

    private String categoria;
    private String energia;
    private String mantenimiento;
    private Double velocidad;

    @OneToOne(mappedBy = "locomotora", cascade = CascadeType.ALL)
    private Vagon vagon;

    public Locomotora() {}

    public Long getId() { return id; }

    public Tren getTren() { return tren; }

    public void setTren(Tren tren) {
        this.tren = tren;
    }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEnergia() { return energia; }

    public void setEnergia(String energia) {
        this.energia = energia;
    }

    public String getMantenimiento() { return mantenimiento; }

    public void setMantenimiento(String mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public Double getVelocidad() { return velocidad; }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    public Vagon getVagon() { return vagon; }

    public void setVagon(Vagon vagon) {
        this.vagon = vagon;
    }
}