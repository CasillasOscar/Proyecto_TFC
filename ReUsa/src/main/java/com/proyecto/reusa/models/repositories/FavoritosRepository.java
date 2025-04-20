package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Favorito;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritosRepository extends JpaRepository<Favorito, Integer> {

    List<Favorito> getFavoritosByIdUsuarioComprador_Nickname(@Size(max = 25) @NotNull String idUsuarioCompradorNickname);

    Optional<Favorito> getFavoritoByIdProducto_IdAndIdUsuarioComprador_Nickname(Integer idProductoId, @Size(max = 25) @NotNull String idUsuarioCompradorNickname);
}
