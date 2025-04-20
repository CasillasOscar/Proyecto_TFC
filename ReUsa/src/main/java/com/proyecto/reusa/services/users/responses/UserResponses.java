package com.proyecto.reusa.services.users.responses;

import com.proyecto.reusa.models.Favorito;
import com.proyecto.reusa.models.Producto;
import com.proyecto.reusa.models.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResponses {
    private Usuario user;
    private Boolean responseBoolean;
    private List<Favorito> listaFavoritos;

    public UserResponses(Boolean responseBoolean) {
        this.responseBoolean = responseBoolean;
    }

    public UserResponses(Usuario user, Boolean responseBoolean) {
        this.responseBoolean = responseBoolean;
        this.user = user;
    }

    public UserResponses(List<Favorito> listaFavoritos, Boolean responseBoolean) {
        this.listaFavoritos = listaFavoritos;
        this.responseBoolean = responseBoolean;
    }

    public Map<String, String> responseUpdatedPwd200() {

        Map<String, String> response = new HashMap<>();
        response.put("nickname", user.getNickname());
        response.put("response_successfully", responseBoolean.toString());

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
        response.put("response_successfully", responseBoolean.toString());

        return response;
    }

    public Map<String, Object> responseFavoritos200() {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> productList = new ArrayList<>();

        for (Favorito fav: listaFavoritos){
            Map<String, String> productData = extractProductData(fav.getIdProducto());
            productList.add(productData);
        }

        response.put("favorites_products", productList);
        response.put("response_successfully", responseBoolean.toString());
        return response;
    }

    public Map<String, String> responseRemoveFavorite200() {
        Map<String, String> response = new HashMap<>();
        response.put("response_successfully", responseBoolean.toString());
        response.put("remove_favorite", responseBoolean.toString());

        return response;
    }

    private Map<String, String> extractProductData(Producto p) {
        Map<String, String> productData = new HashMap<>();
        productData.put("id", p.getId().toString());
        productData.put("nombre", p.getNombre());
        productData.put("precio", p.getPrecio().toString());
        productData.put("descripcion", p.getDescripcion());
        productData.put("estado", p.getEstado());
        productData.put("fecha_publicacion", p.getFechaPublicacion().toString());
        productData.put("categoria", p.getCategoria());
        productData.put("subcategoria", p.getSubcategoria());
        productData.put("imagen_1", p.getImagen1());
        productData.put("imagen_2", p.getImagen2());
        productData.put("imagen_3", p.getImagen3());
        productData.put("imagen_4", p.getImagen4());
        productData.put("etapa", p.getEtapa());

        return productData;
    }

}
