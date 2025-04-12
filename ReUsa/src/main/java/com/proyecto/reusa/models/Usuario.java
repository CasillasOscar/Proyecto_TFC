package com.proyecto.reusa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "usuarios")
@JsonIgnoreProperties({"idProvincia"})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 25)
    @NotNull
    @Column(name = "nickname", nullable = false, length = 25)
    private String nickname;

    @Size(max = 25)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    @Size(max = 25)
    @NotNull
    @Column(name = "apellido", nullable = false, length = 25)
    private String apellido;

    @Size(max = 255)
    @Column(name = "imagen_perfil", nullable = false)
    private String imagenPerfil;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia idProvincia;

    @JsonProperty("provincia")
    public String getProvincia() {
        return idProvincia != null ? idProvincia.getNombre() : null;
    }
    @JsonProperty("comunidad_autonoma")
    public String getCCAA() {
        return idProvincia != null ? idProvincia.getIdComunidadAutonoma().getNombre() : null;
    }

    @Size(min = 8, max = 200)
    @NotNull
    @Column(name = "password", nullable = false, length = 50)
    private String password;


    @NotNull
    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @Size(max = 50)
    @NotNull
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "valoracion", nullable = false)
    private Float valoracion;

    @Column(name = "n_ventas", nullable = false)
    private Integer nVentas;

    @Column(name = "n_compras", nullable = false)
    private Integer nCompras;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public Provincia getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Provincia idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getValoracion() {
        return valoracion;
    }

    public void setValoracion(Float valoracion) {
        this.valoracion = valoracion;
    }

    public Integer getNVentas() {
        return nVentas;
    }

    public void setNVentas(Integer nVentas) {
        this.nVentas = nVentas;
    }

    public Integer getNCompras() {
        return nCompras;
    }

    public void setNCompras(Integer nCompras) {
        this.nCompras = nCompras;
    }

}