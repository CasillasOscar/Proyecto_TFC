package com.proyecto.reusa.services.users;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Token;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.models.repositories.TokenRepository;
import com.proyecto.reusa.models.repositories.UserRepository;
import com.proyecto.reusa.services.users.security.JwtService;
import com.proyecto.reusa.services.users.serializers.UserLoginDTO;
import com.proyecto.reusa.services.users.serializers.UserSigninDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Service_user implements Interface_service_user {


    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


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
    public Optional<Usuario> login(UserLoginDTO user) throws CustomException {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> signin(UserSigninDTO userDTO) throws CustomException {

        var user = Usuario.builder()
                .nickname(userDTO.nickname())
                .nombre(userDTO.nombre())
                .apellido(userDTO.apellido())
                .email(userDTO.email())
                .password(passwordEncoder.encode(userDTO.password()))
                .telefono(userDTO.telefono())
                .build();

        Usuario savedUser = repositoryUser.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);

        if(Optional.of(savedUser).isPresent()){
            return Optional.of(savedUser);
        } else {
            throw new CustomException("El usuario no ha podido ser creado");
        }
    }

    private void saveUserToken(Usuario user, String jwtToken){
        var token = Token.builder()
                .usuario(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
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
