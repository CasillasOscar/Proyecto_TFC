package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Producto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {


    Optional<Producto> getProductoByIdAndEtapa(Integer id, @NotNull String etapa);

    List<Producto> getProductosByEtapa(@NotNull String etapa);

    List<Producto> getProductosByEstado(@NotNull String estado);
}
