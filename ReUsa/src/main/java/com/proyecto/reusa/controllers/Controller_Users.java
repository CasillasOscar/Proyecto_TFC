package com.proyecto.reusa.controllers;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.services.users.Service_user;
import com.proyecto.reusa.services.users.serializers.UserLoginDTO;
import com.proyecto.reusa.services.users.serializers.UserSigninDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CacheConfig(cacheNames = {"auth"})
public class Controller_Users {

    @Autowired
    private Service_user serviceUser;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) throws CustomException{
        return ResponseEntity.ok(serviceUser.getUserById(id));
    }

    @GetMapping("nickname/{nickname}")
    public ResponseEntity<?> getUserByNickname(@PathVariable String nickname) throws CustomException{
        return ResponseEntity.ok(serviceUser.getUserByNickname(nickname));
    }


    //ACCESS
    @PostMapping("/signin")
    public ResponseEntity<?> signin (@RequestBody UserSigninDTO user) throws CustomException {
        return ResponseEntity.ok(serviceUser.signin(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody UserLoginDTO user) throws CustomException {
        return ResponseEntity.ok(serviceUser.login(user));
    }

//    @PostMapping("/refresh")
//    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader){
//        return service.refreshToken(authHeader);
//    }
}
