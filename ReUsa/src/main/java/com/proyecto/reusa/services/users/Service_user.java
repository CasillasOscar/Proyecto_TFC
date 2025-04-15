package com.proyecto.reusa.services.users;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Token;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.models.repositories.TokenRepository;
import com.proyecto.reusa.models.repositories.UserRepository;
import com.proyecto.reusa.services.users.serializers.SerializerUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Service_user {


    @Autowired
    private UserRepository repositoryUser;
    @Autowired
    private TokenRepository tokenRepository;


    public SerializerUser getUserByNickname(String nickname, String authHeader) throws CustomException {
        Optional<Usuario> user = repositoryUser.getUsuarioByNickname(nickname);

        if(user.isEmpty()) {
            throw new CustomException("No existe un usuario con el nickname: " + nickname);
        }

        String requestToken = authHeader.substring(7);
        Optional<Token> token = tokenRepository.getTokenByTokenAndUsuario_Id(requestToken, user.get().getId());

        if(token.isEmpty()){
            throw new CustomException("Token no válido para el usuario " + user.get().getNickname());
        }

        if(!token.get().getToken().equals(requestToken)){
            throw new CustomException("Token no válido");
        }

        return new SerializerUser(
                user.get().getNickname(),
                user.get().getNombre(),
                user.get().getApellido(),
                user.get().getEmail(),
                user.get().getProvincia(),
                user.get().getCCAA(),
                user.get().getValoracion(),
                user.get().getNVentas(),
                user.get().getNCompras(),
                user.get().getImagenPerfil()
        );
    }


    public Optional<Usuario> updateUser(Usuario user) {
        return Optional.empty();
    }


    public Boolean deleteUser(Usuario user) {
        return null;
    }
}
