package com.proyecto.reusa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "contraoferta")
public class Contraoferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario_comprador", nullable = false)
    private com.proyecto.reusa.models.Usuario idUsuarioComprador;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_producto", nullable = false)
    private com.proyecto.reusa.models.Producto idProducto;

    @NotNull
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.proyecto.reusa.models.Usuario getIdUsuarioComprador() {
        return idUsuarioComprador;
    }

    public void setIdUsuarioComprador(com.proyecto.reusa.models.Usuario idUsuarioComprador) {
        this.idUsuarioComprador = idUsuarioComprador;
    }

    public com.proyecto.reusa.models.Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(com.proyecto.reusa.models.Producto idProducto) {
        this.idProducto = idProducto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

}