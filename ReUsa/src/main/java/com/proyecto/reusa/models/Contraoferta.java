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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario_vendedor", nullable = false)
    private Usuario idUsuarioVendedor;

    @NotNull
    @Column(name = "expired", nullable = false)
    private Boolean expired = false;

    @NotNull
    @Lob
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @NotNull
    @Lob
    @Column(name = "estado", nullable = false)
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Usuario getIdUsuarioVendedor() {
        return idUsuarioVendedor;
    }

    public void setIdUsuarioVendedor(Usuario idUsuarioVendedor) {
        this.idUsuarioVendedor = idUsuarioVendedor;
    }

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