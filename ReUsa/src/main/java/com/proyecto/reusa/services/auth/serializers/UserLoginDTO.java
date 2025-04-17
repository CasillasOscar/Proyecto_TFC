package com.proyecto.reusa.services.auth.serializers;

public record UserLoginDTO(
        String email,
        String password) {
}
