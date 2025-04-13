package com.proyecto.reusa.services.users;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Token;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.models.repositories.TokenRepository;
import com.proyecto.reusa.models.repositories.UserRepository;
import com.proyecto.reusa.services.users.responses.UserTokenResponse;
import com.proyecto.reusa.services.users.security.JwtService;
import com.proyecto.reusa.services.users.serializers.UserLoginDTO;
import com.proyecto.reusa.services.users.serializers.UserSigninDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
    private final AuthenticationManager authenticationManager;


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
    public Map<String, Object> login(UserLoginDTO user) throws CustomException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.email(),
                        user.password()
                )
        );

        Optional<Usuario> userFind = repositoryUser.findByEmail(user.email());
        if(userFind.isPresent()){
            var jwtToken = jwtService.generateToken(userFind.get());
            var refreshToken = jwtService.generateRefreshToken(userFind.get());
            revokeAllUserTokens(userFind.get());
            saveUserToken(userFind.get(), jwtToken);

            return new UserTokenResponse(userFind.get(), jwtToken, refreshToken).responseLogin200();
        } else {
            throw new CustomException("Usuario y contraseña no coinciden");
        }
    }

    private void revokeAllUserTokens(final Usuario user){
        final List<Token> validUSerToken = tokenRepository.getTokensByExpiredAndRevokedAndUsuario(false, false, user);
        if(!validUSerToken.isEmpty()){
            for (Token token: validUSerToken){
                token.setExpired(true);
                token.setRevoked(true);
            }
            tokenRepository.saveAll(validUSerToken);
        }
    }

    @Override
    public Map<String, Object> signin(UserSigninDTO userDTO) throws CustomException {

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
            return new UserTokenResponse(savedUser, jwtToken, refreshToken).responseLogin200();
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

    public Map<String, Object> refreshToken(final String authHeader) throws CustomException{
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new CustomException("Token no válido");
        } else {
            final String refreshToken = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(refreshToken);

            if(userEmail == null){
                throw new CustomException("RefreshToken no válido");
            }

            final Optional<Usuario> user = repositoryUser.findByEmail(userEmail);

            if(user.isPresent()){
                //Falla Aqui
                if(!jwtService.isTokenValid(refreshToken, user.get())){
                    final String accessToken = jwtService.generateToken(user.get());
                    revokeAllUserTokens(user.get());
                    saveUserToken(user.get(), accessToken);
                    return new UserTokenResponse(user.get(), accessToken, refreshToken).responseLogin200();

                } else {
                    throw new CustomException("RefreshToken no válido");
                }
            } else {
                throw new CustomException("RefreshToken no válido");
            }
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
