package com.proyecto.reusa.services.products;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Producto;
import com.proyecto.reusa.models.Token;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.models.Venta;
import com.proyecto.reusa.models.repositories.*;
import com.proyecto.reusa.services.products.responses.ProductResponses;
import com.proyecto.reusa.services.products.serializers.FiltersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
@RequiredArgsConstructor
public class Service_Product {

    private ProductoRepository productoRepository;
    private TokenRepository tokenRepository;
    private UserRepository userRepository;
    private VentaRepository ventaRepository;

    public List<Producto> getAllProductsActive(){
        return productoRepository.getProductosByEtapa("activo");
    }

//    public List<Producto> getProductsWithFilters(FiltersDTO filters){
//        List<String> filtersNotNull = getNonNullFilters(filters);
//
//    }

    public Map<String, Object> buyProduct(String buyer_nickname, Integer id_product, String authHeader) throws CustomException {
        Usuario user = findOutNickAndToken(buyer_nickname, authHeader);

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

    private Usuario findOutNickAndToken(
            String nickname,
            String authHeader
    ) throws CustomException {

        Optional<Usuario> user = userRepository.getUsuarioByNickname(nickname);

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

    private List<String> getNonNullFilters(FiltersDTO filtersDTO) {
        List<String> nonNullFilters = new ArrayList<>();

        if (filtersDTO.categoria() != null) {
            nonNullFilters.add("categoria");
        }
        if (filtersDTO.subcategoria() != null) {
            nonNullFilters.add("subcategoria");
        }
        if (filtersDTO.maxPrecio() != null) {
            nonNullFilters.add("maxPrecio");
        }
        if (filtersDTO.minPrecio() != null) {
            nonNullFilters.add("minPrecio");
        }
        if (filtersDTO.provincia() != null) {
            nonNullFilters.add("provincia");
        }
        if (filtersDTO.CCAA() != null) {
            nonNullFilters.add("CCAA");
        }
        if (filtersDTO.estado() != null) {
            nonNullFilters.add("estado");
        }

        return nonNullFilters;
    }

}


