package com.proyecto.reusa.controllers.product;


import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.services.products.Service_Product;
import com.proyecto.reusa.services.products.serializers.FiltersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CacheConfig(cacheNames = {"product"})
public class ProductController {

    private Service_Product serviceProduct;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProductsActive(){
        return ResponseEntity.ok(serviceProduct.getAllProductsActive());
    }

    @PostMapping("products/filters")
    public ResponseEntity<?> getProductsFilter(@RequestBody FiltersDTO filters){
        return ResponseEntity.ok(serviceProduct.getProductsWithFilters(filters));
    }


    @PostMapping("/buy/{buyer_nickname}/{id_product}")
    public ResponseEntity<?> buyProduct (
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader,
            @PathVariable String buyer_nickname,
            @PathVariable Integer id_product
    ) throws CustomException {
        return ResponseEntity.ok(serviceProduct.buyProduct(buyer_nickname,id_product,authHeader));
    }
}
