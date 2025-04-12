package com.proyecto.reusa.controllers;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.services.users.Service_user;
import com.proyecto.reusa.services.users.serializers.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CacheConfig(cacheNames = {"user"})
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

    @PostMapping("/signin")
    public ResponseEntity<?> signin (@RequestBody UserLoginDTO user) throws CustomException {
        return ResponseEntity.ok(serviceUser.signin(user));
    }

}
