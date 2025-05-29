package com.proyecto.reusa.services.products.serializers;

import java.math.BigDecimal;

public class ProductDTO {
    private Integer id;
    private BigDecimal precio;
    private String nombre;
    private String descripcion;
    private String estado;
    private String subcategoria;
    private String categoria;
    private String imagen1;
    private String imagen2;

    public ProductDTO(Integer id, BigDecimal precio, String descripcion, String nombre, String estado, String subcategoria, String categoria, String imagen1, String imagen2) {
        this.id = id;
        this.precio = precio;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.estado = estado;
        this.subcategoria = subcategoria;
        this.categoria = categoria;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
    }

    public ProductDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
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

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen1() {
        return imagen1;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }
}
