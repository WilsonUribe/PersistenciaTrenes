package com.productos;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_ferrocarril")
public class Ferrocarril {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipoVia;

    @OneToMany(mappedBy = "ferrocarril", cascade = CascadeType.ALL)
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
    public String getTipoVia() {
        return tipoVia;
    }
    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }
    public List<Tren> getTrenes() {
        return trenes;
    }
    public void setTrenes(List<Tren> trenes) {
        this.trenes = trenes;
    }
}