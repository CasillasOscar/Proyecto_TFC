package com.proyecto.reusa.services.users.serializers;

public class SerializerUser {

    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private String provincia;
    private String comunidadAutonoma;
    private Float valoracion;
    private Integer n_ventas;
    private Integer n_compras;
    private String imagen_perfil;

    public SerializerUser(String nickname, String apellido, String nombre, String email, String provincia, String comunidadAutonoma, Float valoracion, Integer n_ventas, Integer n_compras, String imagen_perfil) {
        this.nickname = nickname;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.provincia = provincia;
        this.comunidadAutonoma = comunidadAutonoma;
        this.valoracion = valoracion;
        this.n_ventas = n_ventas;
        this.n_compras = n_compras;
        this.imagen_perfil = imagen_perfil;
    }

    public SerializerUser(String nickname, String nombre, String apellido, String email) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComunidadAutonoma() {
        return comunidadAutonoma;
    }

    public void setComunidadAutonoma(String comunidadAutonoma) {
        this.comunidadAutonoma = comunidadAutonoma;
    }

    public Float getValoracion() {
        return valoracion;
    }

    public void setValoracion(Float valoracion) {
        this.valoracion = valoracion;
    }

    public Integer getN_ventas() {
        return n_ventas;
    }

    public void setN_ventas(Integer n_ventas) {
        this.n_ventas = n_ventas;
    }

    public Integer getN_compras() {
        return n_compras;
    }

    public void setN_compras(Integer n_compras) {
        this.n_compras = n_compras;
    }

    public String getImagen_perfil() {
        return imagen_perfil;
    }

    public void setImagen_perfil(String imagen_perfil) {
        this.imagen_perfil = imagen_perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
