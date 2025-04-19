package com.proyecto.reusa.services.products.responses;

import com.proyecto.reusa.models.Producto;
import com.proyecto.reusa.models.Venta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductResponses {
        private Venta venta;
        private Boolean responseBoolean;
        private List<Producto> products;
        private Producto product;

    public ProductResponses(Producto product, Boolean responseBoolean) {
        this.product = product;
        this.responseBoolean = responseBoolean;
    }

    public ProductResponses(Venta venta, Boolean responseBoolean) {
        this.venta = venta;
        this.responseBoolean = responseBoolean;
    }

    public ProductResponses(List<Producto> products, Boolean responseBoolean) {
        this.responseBoolean = responseBoolean;
        this.products = products;
    }

    public Map<String, Object> responseProductBought200() {
            Map<String, Object> response = new HashMap<>();

            Map<String, String> ventaData = new HashMap<>();
            ventaData.put("comprador", venta.getIdUsuarioComprador().getNickname());
            ventaData.put("vendedor", venta.getIdUsuarioVendedor().getNickname());
            ventaData.put("fecha", venta.getFechaVenta().toString());
            ventaData.put("producto", venta.getIdProducto().getNombre());
            ventaData.put("precio", venta.getPrecio().toString());

            response.put("sale", ventaData);
            response.put("response_successfully", responseBoolean.toString());

            return response;
        }

    public Map<String, Object> responseProductFilters200() {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> productList = new ArrayList<>();

        for (Producto p: products){
            Map<String, String> productData = new HashMap<>();
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
            productList.add(productData);

        }

        response.put("products", productList);
        response.put("response_successfully", responseBoolean.toString());

        return response;
    }

    public Map<String, Object> responseGetProduct200() {
        Map<String, Object> response = new HashMap<>();

        Map<String, String> productData = new HashMap<>();
        productData.put("nombre", product.getNombre());
        productData.put("precio", product.getPrecio().toString());
        productData.put("descripcion", product.getDescripcion());
        productData.put("estado", product.getEstado());
        productData.put("fecha_publicacion", product.getFechaPublicacion().toString());
        productData.put("categoria", product.getCategoria());
        productData.put("subcategoria", product.getSubcategoria());
        productData.put("imagen_1", product.getImagen1());
        productData.put("imagen_2", product.getImagen2());
        productData.put("imagen_3", product.getImagen3());
        productData.put("imagen_4", product.getImagen4());

        response.put("product", productData);
        response.put("response_successfully", responseBoolean.toString());

        return response;
    }




}
