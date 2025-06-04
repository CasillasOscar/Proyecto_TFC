package com.proyecto.reusa.services.products.responses;

import com.proyecto.reusa.models.Producto;
import com.proyecto.reusa.models.Provincia;
import com.proyecto.reusa.models.Subcategoria;
import com.proyecto.reusa.models.Venta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductResponses {
        private Venta venta;
        private Boolean responseBoolean;
        private List<Producto> products;
        private List<Subcategoria> subcategorias;
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
    public ProductResponses(List<Subcategoria> subcategorias, Boolean responseBoolean, Integer dummy) {
        this.responseBoolean = responseBoolean;
        this.subcategorias = subcategorias;
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
            Map<String, String> productData = extractProductData(p);
            productList.add(productData);

        }

        response.put("products", productList);
        response.put("response_successfully", responseBoolean.toString());

        return response;
    }

    public Map<String, Object> responseGetProduct200() {
        Map<String, Object> response = new HashMap<>();

        response.put("product", extractProductData(product));
        response.put("response_successfully", responseBoolean.toString());

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
        productData.put("imagen1", p.getImagen1());
        productData.put("imagen2", p.getImagen2());

        return productData;
    }

    public Map<String, Object> responseListSubcategorias200() {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> subcategoriasList = new ArrayList<>();

        for (Subcategoria sub: subcategorias){
            Map<String, String> subcategoriaData = new HashMap<>();
            subcategoriaData.put("id",sub.getId().toString());
            subcategoriaData.put("nombre", sub.getNombre());

            subcategoriasList.add(subcategoriaData);
        }

        response.put("subcategorias", subcategoriasList);
        response.put("response_successfully", responseBoolean.toString());
        return response;
    }



}
