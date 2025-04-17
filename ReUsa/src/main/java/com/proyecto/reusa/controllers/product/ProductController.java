package com.proyecto.reusa.controllers.product;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@CacheConfig(cacheNames = {"product"})
public class ProductController {

}
