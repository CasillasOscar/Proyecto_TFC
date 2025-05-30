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
import com.proyecto.reusa.services.users.serializers.ProfilePhotoDTO;
import com.proyecto.reusa.services.users.serializers.SerializerUser;
import com.proyecto.reusa.services.users.serializers.UpdatePwdDTO;
import com.proyecto.reusa.services.users.serializers.UpdateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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

    @Value("${ruta.imagenes.perfil}")
    private String filePathProfilePhoto;

    private final PasswordEncoder passwordEncoder;

    public SerializerUser getUserByNickname(
            String nickname
    ) throws CustomException {

        Usuario user = findNickname(nickname);

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
            UpdatePwdDTO request
    ) throws CustomException {

        Usuario user = findNickname(nickname);

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
            String nickname
    ) throws CustomException {

        Usuario userFound = findNickname(nickname);

        Optional<Usuario> userNicknameExist = repositoryUser.getUsuarioByNickname(userDTO.getNickname());
        if(userNicknameExist.isPresent() && !userNicknameExist.get().getEmail().equals(userFound.getEmail())){
            throw new CustomException("El nickname " + userDTO.getNickname() + " no disponible");
        }

        Provincia provinciaFound = provinciaRepository.getProvinciaByNombre(userDTO.getProvincia());

        userFound.setNombre(userDTO.getNombre());
        userFound.setApellido(userDTO.getApellido());
        userFound.setNickname(userDTO.getNickname());
        userFound.setIdProvincia(provinciaFound);
        userFound.setTelefono(userDTO.getTelefono());

        userRepository.save(userFound);

        return new UserResponses(userFound, true).responseUpdatedUser200();
    }

    public Map<String, Object> getFavoritesProducts(
            String nickname
    ) throws CustomException {
        Usuario userFound = findNickname(nickname);
        List<Favorito> favoritos = favoritosRepository.getFavoritosByIdUsuarioComprador_Nickname(nickname);

        return new UserResponses(favoritos, true).responseFavoritos200();
    }

    public Map<String, Object> getListProductsFavorites(
            String nickname
    ) throws CustomException {
        Usuario userFound = findNickname(nickname);
        List<Favorito> favoritos = favoritosRepository.getFavoritosByIdUsuarioComprador_Nickname(userFound.getNickname());

        return new UserResponses(favoritos,true).responseListProductsFavoritos200();
    }

    public Map<String, String> removeFavoriteProduct(
            String nickname,
            Integer id_product
    ) throws CustomException {
        Usuario userFound = findNickname(nickname);

        Optional<Favorito> favorite_product = favoritosRepository.getFavoritoByIdProducto_IdAndIdUsuarioComprador_Nickname(id_product, userFound.getNickname());
        if(favorite_product.isEmpty()){
            throw new CustomException("Producto no encontrado en favoritos");
        }
        favoritosRepository.deleteById(favorite_product.get().getId());

        return new UserResponses(true).responseRemoveFavorite200();
    }

    public Map<String, String> updateProfilePhoto(
            String nickname,
            MultipartFile image
    ) throws CustomException{

        if (image.isEmpty()) {
            throw new CustomException("Por favor, selecciona una imagen.");
        }

        Usuario user = findNickname(nickname);
        // 1. Obtener la ruta de la imagen anterior
        String rutaImagenAnterior = user.getImagenPerfil();

        if (rutaImagenAnterior != null && !rutaImagenAnterior.isEmpty()){
            Path rutaAbsolutaImagenAnterior = Paths.get(filePathProfilePhoto).resolve(rutaImagenAnterior).toAbsolutePath();
            try {
                Files.deleteIfExists(rutaAbsolutaImagenAnterior);
            } catch (IOException e) {
                System.err.println("Error al borrar la imagen anterior: " + e.getMessage());
                throw new CustomException("Error al actualizar la foto.");
            }
        }

        // 1. Validate file format in the backend
        String fileExtension = getFileExtension(image.getOriginalFilename());
        if (!fileExtension.equalsIgnoreCase("jpg") && !fileExtension.equalsIgnoreCase("jpeg") && !fileExtension.equalsIgnoreCase("png")) {
            throw new CustomException("Solo se permiten archivos con formato JPG o PNG.");
        }
        try {
            // 2. Generate the filename according to the convention
            String nombreArchivoUnico = nickname + "_profilePhoto." + (fileExtension.equalsIgnoreCase("jpeg") ? "jpg" : fileExtension.toLowerCase());

            // 3. Construct the absolute path to save the image
            Path rutaAbsoluta = Paths.get(filePathProfilePhoto).resolve(nombreArchivoUnico).toAbsolutePath();

            // 4. Save the image to the filesystem
            Files.copy(image.getInputStream(), rutaAbsoluta);

            // 5. Construct the relative path to store in the database
            String rutaRelativa = nombreArchivoUnico;

            user.setImagenPerfil(rutaRelativa);
            userRepository.save(user);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Imagen de perfil actualizada correctamente.");
            response.put("pathImage", rutaRelativa);
            return response;

        } catch (IOException e) {
            throw new CustomException("Error al guardar la imagen: " + e.getMessage());
        }
    }

    public ProfilePhotoDTO getProfilePhoto(
            String nickname
    ) throws CustomException{
        Usuario user = findNickname(nickname);
        String rutaImagen = user.getImagenPerfil();

        if(rutaImagen == null){throw new CustomException("No hay ninguna imagen guardada");}

        Path rutaAbsolutaImagen = Paths.get(filePathProfilePhoto).resolve(rutaImagen).toAbsolutePath();

        if (!Files.exists(rutaAbsolutaImagen) || !Files.isReadable(rutaAbsolutaImagen)) {
            throw new CustomException("La foto de perfil no se encontró o no se puede leer");
        }

        try {
            byte[] photoBytes = Files.readAllBytes(rutaAbsolutaImagen);


            return new ProfilePhotoDTO(rutaImagen, photoBytes);
        } catch (IOException e) {
            throw new CustomException("Error al leer la foto de perfil: " + e.getMessage());
        }
    }

    public Map<String, Object> getListProvincias(){
        List<Provincia> provincias = provinciaRepository.findAll();

        return new UserResponses(provincias, true, 1).responseListProvincias200();
    }

    public Map<String, String> updateProvincia(String nombrePorvincia, String nickname) throws CustomException{
        Optional <Usuario> usuario= repositoryUser.getUsuarioByNickname(nickname);
        if(usuario.isEmpty()){
            throw new CustomException("El usuario no existe");
        }
        Provincia provincia = provinciaRepository.getProvinciaByNombre(nombrePorvincia);
        usuario.get().setIdProvincia(provincia);
        repositoryUser.save(usuario.get());
        return new UserResponses(usuario.get(), true).responseProvinciaUpdated200();

    }



    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    private Usuario findNickname(
            String nickname
    ) throws CustomException {

        Optional<Usuario> user = repositoryUser.getUsuarioByNickname(nickname);

        if(user.isEmpty()) {
            throw new CustomException("No existe un usuario con el nickname: " + nickname);
        }
        return user.get();
    }



}
