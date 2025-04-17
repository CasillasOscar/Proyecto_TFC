package com.proyecto.reusa.services.users.responses;

import com.proyecto.reusa.models.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UserResponses {
    private Usuario user;
    private Boolean responseBoolean;

    public UserResponses(Usuario user) {
        this.user = user;
    }

    public UserResponses(Boolean responseBoolean, Usuario user) {
        this.responseBoolean = responseBoolean;
        this.user = user;
    }

    public Map<String, String> responseUpdatedPwd200() {

        Map<String, String> response = new HashMap<>();
        response.put("nickname", user.getNickname());
        response.put("Update password", responseBoolean.toString());

        return response;
    }

    public Map<String, Object> responseUpdatedUser200() {
        Map<String, Object> response = new HashMap<>();

        Map<String, String> userData = new HashMap<>();
        userData.put("email", user.getEmail());
        userData.put("nombre", user.getNombre());
        userData.put("apellido", user.getApellido());
        userData.put("nickname", user.getNickname());
        userData.put("provincia", user.getProvincia());
        userData.put("comunidadAutonoma", user.getCCAA());

        response.put("user", userData);
        response.put("updated user", responseBoolean.toString());

        return response;
    }

}
