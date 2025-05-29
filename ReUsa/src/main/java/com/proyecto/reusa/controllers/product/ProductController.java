package com.proyecto.reusa.controllers.product;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.services.products.Service_Product;
import com.proyecto.reusa.services.products.serializers.FiltersDTO;
import com.proyecto.reusa.services.products.serializers.SellProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@CacheConfig(cacheNames = {"products"})
public class ProductController {

    @Autowired
    private Service_Product serviceProduct;

    @GetMapping()
    public ResponseEntity<?> getAllProductsActive(){
        return ResponseEntity.ok(serviceProduct.getAllProductsActive());
    }

    @PostMapping("/{nickname}/sellProduct")
    public ResponseEntity<?> userSellProduct(
            @PathVariable String nickname,
            @RequestParam("sellProductDTO") String sellDTOJson,
            @RequestPart("imagen1") MultipartFile image1,
            @RequestPart("imagen2") MultipartFile image2
    ) throws CustomException, JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SellProductDTO sellDTO = objectMapper.readValue(sellDTOJson, SellProductDTO.class);

            return ResponseEntity.ok(serviceProduct.setSellProduct(nickname, sellDTO, image1, image2));

        } catch (JsonProcessingException e) {
            throw new CustomException("Error parsing JSON: " + e.getMessage());
        }
    }

    @GetMapping("/listSubcategorias")
    public ResponseEntity<?> listSubcategorias(){
        return ResponseEntity.ok(serviceProduct.listSubcategorias());
    }


    @PostMapping("/filters")
    public ResponseEntity<?> getProductsFilter(@RequestBody FiltersDTO filters){
        return ResponseEntity.ok(serviceProduct.getProductsWithFilters(filters));
    }

    @GetMapping("/{id_product}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id_product) throws CustomException {
        return ResponseEntity.ok(serviceProduct.getProductById(id_product));
    }


    @PostMapping("/buy/{buyer_nickname}/{id_product}")
    public ResponseEntity<?> buyProduct (
            @PathVariable String buyer_nickname,
            @PathVariable Integer id_product
    ) throws CustomException {
        return ResponseEntity.ok(serviceProduct.buyProduct(buyer_nickname,id_product));
    }
}
