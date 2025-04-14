package com.proyecto.reusa.services.users;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.models.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Service_user {


    @Autowired
    private UserRepository repositoryUser;

    public Optional<Usuario> getUserById(Integer id) throws CustomException{
        Optional<Usuario> user = repositoryUser.getUserById(id);

        if(user.isPresent()){
            return user;
        } else {
            throw new CustomException("No existe el usuario con id: " + id);
        }
    }


    public Optional<Usuario> getUserByNickname(String nickname) throws CustomException {
        Optional<Usuario> user = repositoryUser.getUsuarioByNickname(nickname);

        if(user.isPresent()){
            return user;
        } else {
            throw new CustomException("No existe un usuario con el nickname: " + nickname);
        }
    }


    public Optional<Usuario> updateUser(Usuario user) {
        return Optional.empty();
    }


    public Boolean deleteUser(Usuario user) {
        return null;
    }
}
