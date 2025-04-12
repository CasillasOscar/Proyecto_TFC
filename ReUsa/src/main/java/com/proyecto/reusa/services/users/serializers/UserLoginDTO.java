package com.proyecto.reusa.services.users.serializers;

public class UserLoginDTO {
    private String nickname;
    private String password;
    private String nombre;
    private String apellido;
    private Integer telefono;
    private String email;

    public UserLoginDTO(String nickname, String email, String apellido, String password, String nombre, Integer telefono) {
        this.nickname = nickname;
        this.email = email;
        this.apellido = apellido;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
