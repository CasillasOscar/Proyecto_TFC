package com.proyecto.reusa.services.users;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.services.users.serializers.UserLoginDTO;
import com.proyecto.reusa.services.users.serializers.UserSigninDTO;

import java.util.Optional;

public interface Interface_service_user {

    Optional<?> getUserById(Integer id) throws CustomException;
    Optional<?> getUserByNickname(String nickname) throws CustomException;

    Optional<?> login(UserLoginDTO user) throws CustomException;
    Optional<?> signin(UserSigninDTO user) throws CustomException;

    Optional<?> updateUser(Usuario user);

    Boolean deleteUser (Usuario user);


}
