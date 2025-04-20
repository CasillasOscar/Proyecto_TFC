package com.proyecto.reusa.services.users;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Favorito;
import com.proyecto.reusa.models.Provincia;
import com.proyecto.reusa.models.Token;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.models.repositories.FavoritosRepository;
import com.proyecto.reusa.models.repositories.ProvinciaRepository;
import com.proyecto.reusa.models.repositories.TokenRepository;
import com.proyecto.reusa.models.repositories.UserRepository;
import com.proyecto.reusa.services.users.responses.UserResponses;
import com.proyecto.reusa.services.users.serializers.SerializerUser;
import com.proyecto.reusa.services.users.serializers.UpdatePwdDTO;
import com.proyecto.reusa.services.users.serializers.UpdateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Service_user {


    @Autowired
    private UserRepository repositoryUser;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoritosRepository favoritosRepository;

    private final PasswordEncoder passwordEncoder;

    public SerializerUser getUserByNickname(
            String nickname,
            String authHeader
    ) throws CustomException {

        Usuario user = findOutNickAndToken(nickname, authHeader);

        return new SerializerUser(
                user.getNickname(),
                user.getNombre(),
                user.getApellido(),
                user.getEmail(),
                user.getProvincia(),
                user.getCCAA(),
                user.getValoracion(),
                user.getNVentas(),
                user.getNCompras(),
                user.getImagenPerfil()
        );
    }


    public Map<String, String> updatePassword(
            String nickname,
            UpdatePwdDTO request,
            String authHeader
    ) throws CustomException {

        Usuario user = findOutNickAndToken(nickname, authHeader);

        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            throw new CustomException("Las contraseñas no coinciden");
        }


        if(passwordEncoder.matches(request.getNewPassword(), user.getPassword())){
            throw new CustomException("No puedes actualizar a la misma contraseña");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repositoryUser.save(user);

        return new UserResponses(user, true).responseUpdatedPwd200();
    }

    public Map<String, Object> updateUser(
            UpdateUserDTO userDTO,
            String authHeader,
            String nickname
    ) throws CustomException {

        Usuario userFound = findOutNickAndToken(nickname, authHeader);

        Optional<Usuario> userNicknameExist = repositoryUser.getUsuarioByNickname(userDTO.getNickname());
        if(userNicknameExist.isPresent() && !userNicknameExist.get().getEmail().equals(userFound.getEmail())){
            throw new CustomException("El nickname " + userDTO.getNickname() + " no disponible");
        }

        Optional<Provincia> provinciaFound = provinciaRepository.getProvinciaByNombre(userDTO.getProvincia());
        if(provinciaFound.isEmpty()){
            throw new CustomException("La provincia " + userDTO.getProvincia() + " no existe en nuestra base de datos");
        }

        userFound.setNombre(userDTO.getNombre());
        userFound.setApellido(userDTO.getApellido());
        userFound.setNickname(userDTO.getNickname());
        userFound.setIdProvincia(provinciaFound.get());
        userFound.setTelefono(userDTO.getTelefono());

        userRepository.save(userFound);

        return new UserResponses(userFound, true).responseUpdatedUser200();
    }

    public Map<String, Object> getFavoritesProducts(
            String nickname,
            String authHeader
    ) throws CustomException {
        Usuario userFound = findOutNickAndToken(nickname, authHeader);
        List<Favorito> favoritos = favoritosRepository.getFavoritosByIdUsuarioComprador_Nickname(nickname);

        return new UserResponses(favoritos, true).responseFavoritos200();
    }

    public Map<String, String> removeFavoriteProduct(
            String nickname,
            Integer id_product,
            String authHeader
    ) throws CustomException {
        Usuario userFound = findOutNickAndToken(nickname, authHeader);

        Optional<Favorito> favorite_product = favoritosRepository.getFavoritoByIdProducto_IdAndIdUsuarioComprador_Nickname(id_product, userFound.getNickname());
        if(favorite_product.isEmpty()){
            throw new CustomException("Producto no encontrado en favoritos");
        }
        favoritosRepository.deleteById(favorite_product.get().getId());

        return new UserResponses(true).responseRemoveFavorite200();
    }


    private Usuario findOutNickAndToken(
            String nickname,
            String authHeader
    ) throws CustomException {

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

        return user.get();
    }

}
