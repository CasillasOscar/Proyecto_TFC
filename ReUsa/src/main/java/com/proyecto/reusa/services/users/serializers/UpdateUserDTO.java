package com.proyecto.reusa.services.users.serializers;

public class UpdateUserDTO {
    private String nickname;
    private String nombre;
    private String apellido;
    private String provincia;
    private Integer telefono;

    public UpdateUserDTO(String nickname, String nombre, String apellido, String provincia, Integer telefono) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.provincia = provincia;
        this.telefono = telefono;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
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


    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
