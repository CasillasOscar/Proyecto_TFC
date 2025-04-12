package com.proyecto.reusa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario_comprador", nullable = false)
    private Usuario idUsuarioComprador;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario_vendedor", nullable = false)
    private Usuario idUsuarioVendedor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto idProducto;

    @NotNull
    @Column(name = "fecha_venta", nullable = false)
    private LocalDate fechaVenta;

    @NotNull
    @Column(name = "precio", nullable = false)
    private Integer precio;

    @NotNull
    @Column(name = "valoracion_comprador", nullable = false)
    private Integer valoracionComprador;

    @NotNull
    @Column(name = "valoracion_vendedor", nullable = false)
    private Integer valoracionVendedor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdUsuarioComprador() {
        return idUsuarioComprador;
    }

    public void setIdUsuarioComprador(Usuario idUsuarioComprador) {
        this.idUsuarioComprador = idUsuarioComprador;
    }

    public Usuario getIdUsuarioVendedor() {
        return idUsuarioVendedor;
    }

    public void setIdUsuarioVendedor(Usuario idUsuarioVendedor) {
        this.idUsuarioVendedor = idUsuarioVendedor;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getValoracionComprador() {
        return valoracionComprador;
    }

    public void setValoracionComprador(Integer valoracionComprador) {
        this.valoracionComprador = valoracionComprador;
    }

    public Integer getValoracionVendedor() {
        return valoracionVendedor;
    }

    public void setValoracionVendedor(Integer valoracionVendedor) {
        this.valoracionVendedor = valoracionVendedor;
    }

}