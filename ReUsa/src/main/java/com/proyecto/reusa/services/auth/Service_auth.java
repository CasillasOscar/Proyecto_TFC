package com.proyecto.reusa.services.auth;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Token;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.models.repositories.TokenRepository;
import com.proyecto.reusa.models.repositories.UserRepository;
import com.proyecto.reusa.services.auth.responses.AuthResponses;
import com.proyecto.reusa.services.auth.serializers.UserLoginDTO;
import com.proyecto.reusa.services.auth.serializers.UserSigninDTO;
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
public class Service_auth {

    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Map<String, Object> login(UserLoginDTO user) throws CustomException {

        Optional<Usuario> userFind = repositoryUser.findByEmail(user.email());
        if (userFind.isEmpty()){
            throw new CustomException("El email es incorrecto");
        }

        if (!passwordEncoder.matches(user.password(), userFind.get().getPassword())) {
            throw new CustomException("La contraseña es incorrecta");
        }


        var jwtToken = jwtService.generateToken(userFind.get());
        var refreshToken = jwtService.generateRefreshToken(userFind.get());
        revokeAllUserTokens(userFind.get());
        Token token_saved = saveUserToken(userFind.get(), refreshToken);

        return new AuthResponses(
                userFind.get(),
                jwtToken,
                jwtService.extractLocalDateTimeExpiration(jwtToken),
                refreshToken,
                token_saved.getDateExpired()).responseLogin200();

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


    public Map<String, Object> signin(UserSigninDTO userDTO) throws CustomException {

        Optional<Usuario> userNicknameExist = repositoryUser.getUsuarioByNickname(userDTO.nickname());
        if(userNicknameExist.isPresent()){
            throw new CustomException("El nickname " + userDTO.nickname() + " no disponible");
        }

        Optional<Usuario> userEmailExist = repositoryUser.getUsuarioByEmail(userDTO.email());
        if(userEmailExist.isPresent()){
            throw new CustomException("Ya existe un usuario con el email " + userDTO.email());
        }

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
        System.out.println(jwtToken);
        System.out.println(refreshToken);
        Token token_saved = saveUserToken(savedUser, refreshToken);

        return new AuthResponses(
                savedUser,
                jwtToken,
                jwtService.extractLocalDateTimeExpiration(jwtToken),
                refreshToken,
                token_saved.getDateExpired()).responseSignin200();
    }

    private Token saveUserToken(Usuario user, String jwtToken){
        var token = Token.builder()
                .usuario(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .dateExpired(jwtService.extractLocalDateTimeExpiration(jwtToken))
                .expired(false)
                .revoked(false)
                .build();
        return tokenRepository.save(token);
    }

    public Map<String, Object> refreshToken(final String authHeader) throws CustomException{
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new CustomException("Token no válido");
        } else {
            final String refreshToken = authHeader.substring(7);

            Optional<Token> refresh_token_find = tokenRepository.getTokenByTokenAndRevokedAndExpired(refreshToken, false, false);

            if(refresh_token_find.isEmpty()){
                throw new CustomException("RefreshToken no válido o expirado");
            }

            final String userEmail = jwtService.extractUsername(refreshToken);

            if(userEmail == null){
                throw new CustomException("RefreshToken no válido");
            }

            final Optional<Usuario> user = repositoryUser.findByEmail(userEmail);

            if(user.isPresent()){
                if(jwtService.isTokenValid(refreshToken, user.get())){
                    final String accessToken = jwtService.generateToken(user.get());
                    //revokeAllUserTokens(user.get());
                    //saveUserToken(user.get(), accessToken);
                    return new AuthResponses(
                            user.get(),
                            accessToken,
                            jwtService.extractLocalDateTimeExpiration(accessToken),
                            refresh_token_find.get().getToken(),
                            refresh_token_find.get().getDateExpired()).responseLogin200();

                } else {
                    throw new CustomException("RefreshToken no válido");
                }
            } else {
                throw new CustomException("RefreshToken no válido");
            }
        }
    }

}
