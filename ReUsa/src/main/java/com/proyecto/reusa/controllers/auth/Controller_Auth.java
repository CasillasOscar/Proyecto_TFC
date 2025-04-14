package com.proyecto.reusa.controllers.auth;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.services.auth.Service_auth;
import com.proyecto.reusa.services.auth.serializers.UserLoginDTO;
import com.proyecto.reusa.services.auth.serializers.UserSigninDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CacheConfig(cacheNames = {"auth"})
public class Controller_Auth {

    @Autowired
    private Service_auth serviceAuth;


    //ACCESS
    @PostMapping("/signin")
    public ResponseEntity<?> signin (@RequestBody UserSigninDTO user) throws CustomException {
        return ResponseEntity.ok(serviceAuth.signin(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody UserLoginDTO user) throws CustomException {
        return ResponseEntity.ok(serviceAuth.login(user));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) throws CustomException{
        return ResponseEntity.ok(serviceAuth.refreshToken(authHeader));
    }

    //Logout configurado en el SecurityConfig
}
