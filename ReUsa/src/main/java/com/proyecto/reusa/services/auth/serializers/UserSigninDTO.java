package com.proyecto.reusa.services.auth.serializers;

public record UserSigninDTO (
        String nickname,
         String password,
         String nombre,
         String apellido,
         Integer telefono,
         String email
){}
