package com.proyecto.reusa.services.users.responses;

import com.proyecto.reusa.models.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UserTokenResponse {

    private Usuario user;
    private String accessToken;
    private String refreshToken;

    public UserTokenResponse(Usuario user, String accessToken, String refreshToken) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Map<String, Object> responseLogin200() {
        Map<String, Object> response = new HashMap<>();

        Map<String, String> userData = new HashMap<>();
        userData.put("email", user.getEmail());
        userData.put("nombre", user.getNombre());
        userData.put("apellido", user.getApellido());

        response.put("user", userData);
        response.put("token", accessToken);
        response.put("refreshToken", refreshToken);

        return response;
    }

    public Usuario getUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

}
