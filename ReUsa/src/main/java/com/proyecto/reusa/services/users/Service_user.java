package com.proyecto.reusa.services.users;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.models.repositories.UserRepository;
import com.proyecto.reusa.services.users.serializers.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Service_user implements Interface_service_user {

    public Service_user() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository repositoryUser;
    private final PasswordEncoder passwordEncoder;

    public String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean verifyPassword(String rawPassword, String hashPassword) {
        return passwordEncoder.matches(rawPassword, hashPassword);
    }

    @Override
    public Optional<Usuario> getUserById(Integer id) throws CustomException{
        Optional<Usuario> user = repositoryUser.getUserById(id);

        if(user.isPresent()){
            return user;
        } else {
            throw new CustomException("No existe el usuario con id: " + id);
        }
    }

    @Override
    public Optional<Usuario> getUserByNickname(String nickname) throws CustomException {
        Optional<Usuario> user = repositoryUser.getUsuarioByNickname(nickname);

        if(user.isPresent()){
            return user;
        } else {
            throw new CustomException("No existe un usuario con el nickname: " + nickname);
        }
    }

    @Override
    public Optional<Usuario> login(String nickname, String password) throws CustomException {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> signin(UserLoginDTO userDTO) throws CustomException {

        String rawPassword = userDTO.getPassword();
        String hashedPassword = encryptPassword(rawPassword);
        userDTO.setPassword(hashedPassword);

        Usuario user = new Usuario();
        user.setNickname(userDTO.getNickname());
        user.setNombre(userDTO.getNombre());
        user.setApellido(userDTO.getApellido());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setTelefono(userDTO.getTelefono());

        Usuario user_save = repositoryUser.save(user);

        if(Optional.of(user_save).isPresent()){
            return Optional.of(user_save);
        } else {
            throw new CustomException("El usuario no ha podido ser creado");
        }
    }

    @Override
    public Optional<Usuario> updateUser(Usuario user) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteUser(Usuario user) {
        return null;
    }
}
