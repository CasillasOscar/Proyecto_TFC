package com.proyecto.reusa.controllers.users;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.services.users.Service_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CacheConfig(cacheNames = {"user"})
public class Controller_User {

    @Autowired
    private Service_user serviceUser;

    @GetMapping("nickname/{nickname}")
    public ResponseEntity<?> getUserByNickname(@PathVariable String nickname, @RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) throws CustomException{
        return ResponseEntity.ok(serviceUser.getUserByNickname(nickname, authHeader));
    }
}
