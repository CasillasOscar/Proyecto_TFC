package com.proyecto.reusa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"idUsuario", "idSubcategoria"})
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario", nullable = false)
    private com.proyecto.reusa.models.Usuario idUsuario;

    @JsonProperty("usuario_vendedor")
    public String getUsuarioVendedor() {
        return idUsuario != null ? idUsuario.getNickname() : null;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_subcategoria", nullable = false)
    private com.proyecto.reusa.models.Subcategoria idSubcategoria;

    @JsonProperty("subcategoria")
    public String getSubcategoria() {
        return idSubcategoria != null ? idSubcategoria.getNombre() : null;
    }
    @JsonProperty("categoria")
    public String getCategoria() {
        return idSubcategoria != null ? idSubcategoria.getIdCategoria().getNombre() : null;
    }

    @NotNull
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull
    @Lob
    @Column(name = "estado", nullable = false)
    private String estado;

    @NotNull
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @Size(max = 250)
    @NotNull
    @Column(name = "imagen_1", nullable = false, length = 250)
    private String imagen1;

    @Size(max = 250)
    @NotNull
    @Column(name = "imagen_2", nullable = false, length = 250)
    private String imagen2;

    @NotNull
    @Lob
    @Column(name = "etapa", nullable = false)
    private String etapa;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.proyecto.reusa.models.Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(com.proyecto.reusa.models.Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public com.proyecto.reusa.models.Subcategoria getIdSubcategoria() {
        return idSubcategoria;
    }

    public void setIdSubcategoria(com.proyecto.reusa.models.Subcategoria idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
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

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String vendido) {
        this.etapa = vendido;
    }

}