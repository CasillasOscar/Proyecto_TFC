package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Producto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {


    Optional<Producto> getProductoByIdAndEtapa(Integer id, @NotNull String etapa);

    List<Producto> getProductosByEtapa(@NotNull String etapa);

    @Query("SELECT p FROM Producto p " +
            "LEFT JOIN FETCH p.idUsuario u " +
            "LEFT JOIN FETCH u.idProvincia prov " +
            "LEFT JOIN FETCH prov.idComunidadAutonoma ccaa " +
            "LEFT JOIN FETCH p.idSubcategoria sub " +
            "LEFT JOIN FETCH sub.idCategoria cat WHERE " +
            "(:categoria IS NULL OR p.idSubcategoria.idCategoria.nombre = :categoria) AND " +
            "(:subcategoria IS NULL OR p.idSubcategoria.nombre = :subcategoria) AND " +
            "(:maxPrecio IS NULL OR p.precio <= :maxPrecio) AND " +
            "(:minPrecio IS NULL OR p.precio >= :minPrecio) AND " +
            "(:provincia IS NULL OR p.idUsuario.idProvincia.nombre = :provincia) AND " +
            "(:ccaa IS NULL OR p.idUsuario.idProvincia.idComunidadAutonoma.nombre = :ccaa) AND " +
            "(:estado IS NULL OR p.estado = :estado) AND " +
            "p.etapa = 'activo'")
    List<Producto> getProductWithFilters(
            @Param("categoria") String categoria,
            @Param("subcategoria") String subcategoria,
            @Param("maxPrecio") Integer maxPrecio,
            @Param("minPrecio") Integer minPrecio,
            @Param("provincia") String provincia,
            @Param("ccaa") String ccaa,
            @Param("estado") String estado
    );

    Producto getProductoById(Integer id);

    Integer id(Integer id);
}
