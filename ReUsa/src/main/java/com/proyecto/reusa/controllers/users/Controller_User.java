package com.proyecto.reusa.controllers.users;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.services.users.Service_user;
import com.proyecto.reusa.services.users.serializers.UpdatePwdDTO;
import com.proyecto.reusa.services.users.serializers.UpdateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CacheConfig(cacheNames = {"user"})
public class Controller_User {

    @Autowired
    private Service_user serviceUser;

    //Endpoint para obtener la información del usuario a través del nickname
    //Endpoint destinado a abrir la información del perfil
    @GetMapping("/{nickname}")
    public ResponseEntity<?> getUserByNickname(
            @PathVariable String nickname
    ) throws CustomException{
        return ResponseEntity.ok(serviceUser.getUserByNickname(nickname));
    }

    //Endpoint para reestablecer contraseña de usuario (getPassword)
    @PostMapping("updatePassword/{nickname}")
    public ResponseEntity<?> updatePassword(
            @PathVariable String nickname,
            @RequestBody UpdatePwdDTO request
    ) throws CustomException{
    return ResponseEntity.ok(serviceUser.updatePassword(nickname, request));
    }

    //Endpoint para actualizar datos de un usuario
    @PostMapping("updateUser/{nickname}")
    public ResponseEntity<?> updateUser(
            @PathVariable String nickname,
            @RequestBody UpdateUserDTO user
    ) throws CustomException{
        return ResponseEntity.ok(serviceUser.updateUser(user, nickname));
    }

    //Endpoint para listar los favoritos de un usuario
    @GetMapping("/{nickname}/favorites")
    public ResponseEntity<?> getFavoritesProducts(
            @PathVariable String nickname
    ) throws CustomException {
        return ResponseEntity.ok(serviceUser.getFavoritesProducts(nickname));
    }

    //Enpoint para eliminar un favorito
    @GetMapping("/{nickname}/removeFavorite/{id_product}")
    public ResponseEntity<?> deleteFavoriteProduct(
            @PathVariable String nickname,
            @PathVariable Integer id_product
    ) throws CustomException {
        return ResponseEntity.ok(serviceUser.removeFavoriteProduct(nickname, id_product));
    }
}
