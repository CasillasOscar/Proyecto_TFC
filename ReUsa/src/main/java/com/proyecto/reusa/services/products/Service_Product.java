package com.proyecto.reusa.services.products;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.*;
import com.proyecto.reusa.models.repositories.*;
import com.proyecto.reusa.services.products.responses.ProductResponses;
import com.proyecto.reusa.services.products.serializers.FiltersDTO;
import com.proyecto.reusa.services.products.serializers.ImageProductDTO;
import com.proyecto.reusa.services.products.serializers.ProductDTO;
import com.proyecto.reusa.services.products.serializers.SellProductDTO;
import com.proyecto.reusa.services.users.serializers.ProfilePhotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class Service_Product {

    @Value("${ruta.imagenes.productos}")
    private String filePathProductPhotos;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private SubacategoriaRepository subcategoriaRepository;
    @Autowired
    private FavoritosRepository favoritosRepository;

    public List<ProductDTO> getAllProductsActive(){
        List<Producto> listProducts = productoRepository.getProductosByEtapa("activo");

        return listProducts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private ProductDTO convertToDto(Producto producto) {
        ProductDTO dto = new ProductDTO();

        dto.setId(producto.getId());
        dto.setPrecio(producto.getPrecio());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setEstado(producto.getEstado());
        dto.setSubcategoria(producto.getSubcategoria());
        dto.setCategoria(producto.getCategoria());
        dto.setImagen1(producto.getImagen1());
        dto.setImagen2(producto.getImagen2());
        dto.setUsuario(producto.getIdUsuario().getNickname());

        return dto;
    }

    public ImageProductDTO getProductImageBytes(String path) throws CustomException {

        Path rutaAbsolutaImagen = Paths.get(filePathProductPhotos).resolve(path).toAbsolutePath();
        if (!Files.exists(rutaAbsolutaImagen) || !Files.isReadable(rutaAbsolutaImagen)) {
            throw new CustomException("La foto de perfil no se encontró o no se puede leer");
        }
        try {
            byte[] photoBytes = Files.readAllBytes(rutaAbsolutaImagen);

            return new ImageProductDTO(path, photoBytes);
        } catch (IOException e) {
            throw new CustomException("Error al leer la foto de perfil: " + e.getMessage());
        }
    }

    public Producto setSellProduct(String nickname, SellProductDTO productDTO, MultipartFile image1, MultipartFile image2) throws CustomException{

        Usuario user = findNickname(nickname);

        String rutaRelativa1 = saveImage(nickname, image1, 1);
        String rutaRelativa2 = saveImage(nickname, image2, 2);

        Producto createProduct = new Producto();
        createProduct.setNombre(productDTO.getNombre());
        createProduct.setDescripcion(productDTO.getDescripcion());
        createProduct.setEstado(productDTO.getEstado());
        createProduct.setIdSubcategoria(subcategoriaRepository.getSubcategoriaById(productDTO.getSubcategoria()));
        createProduct.setIdUsuario(user);
        createProduct.setFechaPublicacion(LocalDate.now());
        createProduct.setImagen1(rutaRelativa1);
        createProduct.setImagen2(rutaRelativa2);
        createProduct.setPrecio(productDTO.getPrecio());
        createProduct.setEtapa("activo");

        productoRepository.save(createProduct);

        return createProduct;
    }

    public Map<String, Object> listSubcategorias(){
        List<Subcategoria> listSubcategorias = subcategoriaRepository.findAll();

        return new ProductResponses(listSubcategorias, true, 1).responseListSubcategorias200();
    }

    public Map<String, Object> getProductById(Integer id_product) throws CustomException {
        Optional<Producto> product = productoRepository.getProductoByIdAndEtapa(id_product, "activo");
        if(product.isEmpty()){
            throw new CustomException("No existe el producto con id: " + id_product);
        }
        return new ProductResponses(product.get(), true).responseGetProduct200();
    }

    public Map<String, Object> getProductsWithFilters(FiltersDTO filters){
        List<Producto> filteredProducts = productoRepository.getProductWithFilters(
                filters.categoria(),
                filters.subcategoria(),
                filters.maxPrecio(),
                filters.minPrecio(),
                filters.provincia(),
                filters.ccaa(),
                filters.estado()
        );

        return new ProductResponses(filteredProducts, true).responseProductFilters200();
    }

    public Map<String, Object> buyProduct(String buyer_nickname, Integer id_product) throws CustomException {
        Usuario user = findNickname(buyer_nickname);

        Optional<Producto> producto = productoRepository.getProductoByIdAndEtapa(id_product, "activo");

        if(producto.isEmpty()){
            throw new CustomException("El producto " + producto + " no existe o está vendido");
        }

        var newVenta = Venta.builder()
                .idProducto(producto.get())
                .idUsuarioVendedor(producto.get().getIdUsuario())
                .idUsuarioComprador(user)
                .fechaVenta(LocalDate.now())
                .precio(producto.get().getPrecio())
                .build();

           ventaRepository.save(newVenta);

           producto.get().setEtapa("vendido");
           productoRepository.save(producto.get());

        return new ProductResponses(newVenta, true).responseProductBought200();
    }

    public boolean saveFavorite(String nickname, Integer idProduct) throws CustomException {
        Usuario userFound = findNickname(nickname);
        Producto productoFound = productoRepository.getProductoById(idProduct);

        Favorito favorito = new Favorito();
        favorito.setIdProducto(productoFound);
        favorito.setIdUsuarioComprador(userFound);

        favoritosRepository.save(favorito);
        return true;
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    private boolean isValidExtension(String extension1){
        return extension1.equalsIgnoreCase("jpg") || extension1.equalsIgnoreCase("jpeg") || extension1.equalsIgnoreCase("png");
    }

    private String saveImage(String nickname, MultipartFile image, Integer number) throws CustomException {
        String fileExtension = getFileExtension(image.getOriginalFilename());

        if (!isValidExtension(fileExtension)) {
            throw new CustomException("Solo se permiten archivos con formato JPG o PNG.");
        }

        try {
            String nombreAleatorio = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String nombreArchivoUnico = nickname + "_productPhoto" + number +  nombreAleatorio +"."+ (fileExtension.equalsIgnoreCase("jpeg") ? "jpg" : fileExtension.toLowerCase());
            Path rutaAbsoluta = Paths.get(filePathProductPhotos).resolve(nombreArchivoUnico).toAbsolutePath();
            Files.copy(image.getInputStream(), rutaAbsoluta);

            return nombreArchivoUnico;
        } catch (IOException e) {
            throw new CustomException("Error al guardar la imagen: " + e.getMessage());
        }

    }

    private Usuario findNickname(
            String nickname
    ) throws CustomException {

        Optional<Usuario> user = userRepository.getUsuarioByNickname(nickname);

        if(user.isEmpty()) {
            throw new CustomException("No existe un usuario con el nickname: " + nickname);
        }


        return user.get();
    }


}


