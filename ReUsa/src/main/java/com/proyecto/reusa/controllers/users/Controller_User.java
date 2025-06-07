package com.proyecto.reusa.controllers.users;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.services.users.Service_user;
import com.proyecto.reusa.services.users.serializers.ProfilePhotoDTO;
import com.proyecto.reusa.services.users.serializers.UpdatePwdDTO;
import com.proyecto.reusa.services.users.serializers.UpdateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    //Endpoint para actualizar datos de un usuario
    @PostMapping("updateProfilePhoto/{nickname}")
    public ResponseEntity<?> updateProfilePhoto(
            @PathVariable String nickname,
            @RequestParam("imagen") MultipartFile image
    ) throws CustomException{
        return ResponseEntity.ok(serviceUser.updateProfilePhoto(nickname, image));
    }

    //Endpoint para actualizar datos de un usuario
    @GetMapping("profilePhoto/{nickname}")
    public ResponseEntity<byte[]> getProfilePhoto(
            @PathVariable String nickname
    ) throws CustomException{
        try {
            ProfilePhotoDTO photoDTO = serviceUser.getProfilePhoto(nickname);

            byte[] imageData = photoDTO.getPhoto();
            String fileName = photoDTO.getPath();

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
            throw new CustomException("Ha habido un problema en la obtencion de la imagen de perfil");
        }
    }

    //Endpoint para listar los favoritos de un usuario
    @GetMapping("/{nickname}/favorites")
    public ResponseEntity<?> getFavoritesProducts(
            @PathVariable String nickname
    ) throws CustomException {
        return ResponseEntity.ok(serviceUser.getFavoritesProducts(nickname));
    }

    @GetMapping("/{nickname}/listProductsFavorites")
    public ResponseEntity<?> getListProductsFavorites(
            @PathVariable String nickname
    ) throws CustomException {
        return ResponseEntity.ok(serviceUser.getListProductsFavorites(nickname));
    }

    //Enpoint para eliminar un favorito
    @GetMapping("/{nickname}/removeFavorite/{id_product}")
    public ResponseEntity<?> deleteFavoriteProduct(
            @PathVariable String nickname,
            @PathVariable Integer id_product
    ) throws CustomException {
        return ResponseEntity.ok(serviceUser.removeFavoriteProduct(nickname, id_product));
    }

    @GetMapping("/listProvincias")
    public ResponseEntity<?> getProvincias(){
        return ResponseEntity.ok(serviceUser.getListProvincias());
    }

    @PostMapping("/updateProvincia/{nickname}")
    public ResponseEntity<?> updateProvincia(
            @RequestBody String nombreProvincia,
            @PathVariable String nickname
    ) throws CustomException{
        return ResponseEntity.ok(serviceUser.updateProvincia(nombreProvincia, nickname));
    }

    @GetMapping("/compras/{nickname}")
    public ResponseEntity<?> getUserPurchases(
            @PathVariable String nickname
    ) throws CustomException {
        return ResponseEntity.ok(serviceUser.getUserPurchases(nickname));
    }

    @GetMapping("/ventas/{nickname}")
    public ResponseEntity<?> getUserSales(
            @PathVariable String nickname
    ) throws CustomException {
        return ResponseEntity.ok(serviceUser.getUserSales(nickname));
    }


}
