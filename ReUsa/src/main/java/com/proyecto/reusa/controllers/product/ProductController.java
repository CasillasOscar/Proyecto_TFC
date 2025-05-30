package com.proyecto.reusa.controllers.product;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.services.products.Service_Product;
import com.proyecto.reusa.services.products.serializers.FiltersDTO;
import com.proyecto.reusa.services.products.serializers.ImageProductDTO;
import com.proyecto.reusa.services.products.serializers.SellProductDTO;
import com.proyecto.reusa.services.users.serializers.ProfilePhotoDTO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/products")
@CacheConfig(cacheNames = {"products"})
public class ProductController {

    @Autowired
    private Service_Product serviceProduct;

    @GetMapping("/")
    public ResponseEntity<?> getAllProductsActive(){
        return ResponseEntity.ok(serviceProduct.getAllProductsActive());
    }

    @PostMapping("/imageProduct")
    public ResponseEntity<byte[]> getProductImage(
            @RequestParam String path
    ) throws CustomException{
        try {
            ImageProductDTO imageProductDTO = serviceProduct.getProductImageBytes(path);

            byte[] imageData = imageProductDTO.getPhoto();
            String fileName = imageProductDTO.getPath();

            MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
            if (fileName != null) {
                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                switch (fileExtension) {
                    case "jpg":
                    case "jpeg":
                        mediaType = MediaType.IMAGE_JPEG;
                        break;
                    case "png":
                        mediaType = MediaType.IMAGE_PNG;
                        break;
                }
            }

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .body(imageData);

        } catch (CustomException e) {
            throw new CustomException("Ha habido un problema en la obtencion de la imagen del producto");
        }


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

    @GetMapping("/addFavorite/{nickname}/{idProduct}")
    public ResponseEntity<?> saveFavorite(
            @PathVariable String nickname,
            @PathVariable Integer idProduct
    ) throws CustomException{
        return ResponseEntity.ok(serviceProduct.saveFavorite(nickname, idProduct));
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
