package com.proyecto.reusa.services.auth.responses;

import com.proyecto.reusa.models.Usuario;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AuthResponses {

    private Usuario user;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime refreshToken_expired;
    private LocalDateTime token_expired;

    public AuthResponses(Usuario user, String accessToken, String refreshToken) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public AuthResponses(Usuario user, String accessToken, LocalDateTime token_expired, String refreshToken, LocalDateTime refreshToken_expired) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.token_expired = token_expired;
        this.refreshToken_expired = refreshToken_expired;
    }

    public Map<String, Object> responseLogin200() {

        Map<String, String> userData = new HashMap<>();
        userData.put("email", user.getEmail());
        userData.put("nickname", user.getNickname());
        userData.put("nombre", user.getNombre());
        userData.put("apellido", user.getApellido());
        userData.put("telefono", user.getTelefono().toString());
        userData.put("valoracion", user.getValoracion().toString());
        userData.put("ventas", user.getNVentas().toString());
        userData.put("compras", user.getNCompras().toString());

        Map<String, Object> response = new HashMap<>();

        response.put("user", userData);
        response.put("token", accessToken);
        response.put("token_expired", token_expired);
        response.put("refreshToken", refreshToken);
        response.put("refreshToken_expired", refreshToken_expired);

        return response;
    }

    public Map<String, Object> responseSignin200() {

        Map<String, String> userData = new HashMap<>();
        userData.put("email", user.getEmail());
        userData.put("nickname", user.getNickname());
        userData.put("nombre", user.getNombre());
        userData.put("apellido", user.getApellido());
        userData.put("telefono", user.getTelefono().toString());
        userData.put("valoracion", user.getValoracion().toString());
        userData.put("ventas", user.getNVentas().toString());
        userData.put("compras", user.getNCompras().toString());

        Map<String, Object> response = new HashMap<>();
        response.put("user", userData);
        response.put("token", accessToken);
        response.put("token_expired", token_expired);
        response.put("refreshToken", refreshToken);
        response.put("refreshToken_expired", refreshToken_expired);

        return response;
    }
}
