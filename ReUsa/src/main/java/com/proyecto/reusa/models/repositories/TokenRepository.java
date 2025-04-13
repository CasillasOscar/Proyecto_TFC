package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Token;
import com.proyecto.reusa.models.Usuario;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    List<Token> getTokensByExpiredAndRevokedAndUsuario(@NotNull Boolean expired, @NotNull Boolean revoked, Usuario usuario);
}
