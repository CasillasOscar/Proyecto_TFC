package com.proyecto.reusa.services.products.responses;

import com.proyecto.reusa.models.Producto;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.models.Venta;

import java.util.HashMap;
import java.util.Map;

public class ProductResponses {
        private Venta venta;
        private Boolean responseBoolean;


    public ProductResponses(Venta venta, Boolean responseBoolean) {
        this.venta = venta;
        this.responseBoolean = responseBoolean;
    }

    public Map<String, Object> responseProductBought200() {
            Map<String, Object> response = new HashMap<>();

            Map<String, String> ventaData = new HashMap<>();
            ventaData.put("comprador", venta.getIdUsuarioComprador().getNickname());
            ventaData.put("vendedor", venta.getIdUsuarioVendedor().getNickname());
            ventaData.put("fecha", venta.getFechaVenta().toString());
            ventaData.put("producto", venta.getIdProducto().getNombre());
            ventaData.put("precio", venta.getPrecio().toString());

            response.put("Bought product", responseBoolean.toString());

            return response;
        }


}
