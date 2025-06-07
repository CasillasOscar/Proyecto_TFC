package com.proyecto.reusa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venta")
@JsonIgnoreProperties({"idUsuarioComprador", "idUsuarioVendedor", "idProducto"})
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

    @JsonProperty("usuario_comprador")
    public Map<String, Object> getUsuarioComprador() {
        if(idUsuarioComprador != null) {
            Map<String, Object> compradorData = new HashMap<>();
            compradorData.put("nickname", idUsuarioComprador.getNickname());
            compradorData.put("telefono", idUsuarioComprador.getTelefono());
            compradorData.put("email", idUsuarioComprador.getEmail());

            return compradorData;
        } else {
            return null;
        }
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario_vendedor", nullable = false)
    private Usuario idUsuarioVendedor;

    @JsonProperty("usuario_vendedor")
    public Map<String, Object> getUsuarioVendedor() {
        if(idUsuarioVendedor != null) {
            Map<String, Object> compradorData = new HashMap<>();
            compradorData.put("nickname", idUsuarioVendedor.getNickname());
            compradorData.put("telefono", idUsuarioVendedor.getTelefono());
            compradorData.put("email", idUsuarioVendedor.getEmail());

            return compradorData;
        } else {
            return null;
        }
    }
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto idProducto;
    @JsonProperty("producto")
    public String getProducto() {
        if(idProducto != null) {
           return idProducto.getNombre();
        } else {
            return null;
        }
    }

    @NotNull
    @Column(name = "fecha_venta", nullable = false)
    private LocalDate fechaVenta;

    @NotNull
    @Column(name = "precio", nullable = false)
    private BigDecimal precio;


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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }


}