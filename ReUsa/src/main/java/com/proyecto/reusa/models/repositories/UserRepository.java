package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> getUserById(Integer id);

    Optional<Usuario> getUsuarioByNickname(@Size(max = 25) @NotNull String nickname);

    Optional<Usuario> findByEmail(@Size(max = 50) @NotNull String email);

    Optional<Usuario> getUsuarioByEmail(@Size(max = 50) @NotNull String email);

    Optional<Usuario> getUsuarioById(Integer id);
}
