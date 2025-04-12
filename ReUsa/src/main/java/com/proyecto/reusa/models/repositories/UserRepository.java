package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> getUserById(Integer id);

    Optional<Usuario> getUsuarioByNickname(@Size(max = 25) @NotNull String nickname);
}
