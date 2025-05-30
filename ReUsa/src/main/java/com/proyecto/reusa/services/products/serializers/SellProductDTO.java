package com.proyecto.reusa.services.products.serializers;

import java.math.BigDecimal;

public class SellProductDTO {
    private String nombre;
    private String descripcion;
    private String estado;
    private Integer subcategoria;
    private BigDecimal precio;

    public SellProductDTO() {
    }

    public SellProductDTO(String nombre, String descripcion, String estado, Integer subcategoria, BigDecimal precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.subcategoria = subcategoria;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Integer subcategoria) {
        this.subcategoria = subcategoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
