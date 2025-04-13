package com.proyecto.reusa.services.users.serializers;

public record UserSigninDTO (
        String nickname,
         String password,
         String nombre,
         String apellido,
         Integer telefono,
         String email
){}
