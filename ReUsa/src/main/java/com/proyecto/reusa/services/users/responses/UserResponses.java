package com.proyecto.reusa.services.users.responses;

import com.proyecto.reusa.models.Favorito;
import com.proyecto.reusa.models.Producto;
import com.proyecto.reusa.models.Provincia;
import com.proyecto.reusa.models.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResponses {
    private Usuario user;
    private final Boolean responseBoolean;
    private List<Favorito> listaFavoritos;
    private List<Provincia> listaProvincias;

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

    public UserResponses(List<Provincia> listaProvincias, Boolean responseBoolean, int dummy) {
        this.listaProvincias = listaProvincias;
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
        userData.put("telefono", user.getTelefono().toString());
        userData.put("valoracion", user.getValoracion().toString());
        userData.put("ventas", user.getNVentas().toString());
        userData.put("compras", user.getNCompras().toString());

        response.put("user", userData);
        response.put("response_successfully", responseBoolean.toString());

        return response;
    }

    public Map<String, Object> responseFavoritos200() {
        Map<String, Object> response = new HashMap<>();
        List<Integer> productList = new ArrayList<>();

        for (Favorito fav: listaFavoritos){
            Integer id = fav.getIdProducto().getId();
            productList.add(id);
        }

        response.put("favorites_products", productList);
        response.put("response_successfully", responseBoolean.toString());
        return response;
    }

    public Map<String, Object> responseListProductsFavoritos200() {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> productList = new ArrayList<>();

        for (Favorito fav: listaFavoritos){
            Map<String, String> listP = extractProductData(fav.getIdProducto());
            productList.add(listP);
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
        productData.put("categoria", p.getCategoria());
        productData.put("subcategoria", p.getSubcategoria());
        productData.put("imagen1", p.getImagen1());
        productData.put("imagen2", p.getImagen2());

        return productData;
    }

    public Map<String, Object> responseListProvincias200() {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> provinciasList = new ArrayList<>();

        for (Provincia prov: listaProvincias){
            Map<String, String> provinciaData = new HashMap<>();
            provinciaData.put("id",prov.getId().toString());
            provinciaData.put("nombre", prov.getNombre());

            provinciasList.add(provinciaData);
        }

        response.put("provincias", provinciasList);
        response.put("response_successfully", responseBoolean.toString());
        return response;
    }

    public Map<String, String> responseProvinciaUpdated200(){
        Map<String, String> response = new HashMap<>();

        response.put("provinciaUpdated", user.getProvincia());
        response.put("response_successfully", responseBoolean.toString());

        return response;
    }

    public Map<String, Object> responseGetUser200() {

        Map<String, String> userData = new HashMap<>();
        userData.put("email", user.getEmail());
        userData.put("nickname", user.getNickname());
        userData.put("nombre", user.getNombre());
        userData.put("apellido", user.getApellido());
        userData.put("telefono", user.getTelefono().toString());
        userData.put("valoracion", user.getValoracion().toString());
        userData.put("ventas", user.getNVentas().toString());
        userData.put("compras", user.getNCompras().toString());
        String provincia = user.getProvincia();
        if(provincia == null){
            userData.put("provincia", "empty");
        } else {
            userData.put("provincia", user.getProvincia());
        }


        Map<String, Object> response = new HashMap<>();

        response.put("user", userData);
        response.put("response_succesfully", responseBoolean);
        return response;
    }

}
