package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Token;
import com.proyecto.reusa.models.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    List<Token> getTokensByExpiredAndRevokedAndUsuario(@NotNull Boolean expired, @NotNull Boolean revoked, Usuario usuario);

    Token findByToken(@Size(min = 1) @NotNull String token);

    Optional<Token> getTokenByToken(@Size(min = 1) @NotNull String token);

    Optional<Token> getTokenByTokenAndUsuario_Id(@Size(min = 1) @NotNull String token, Integer usuarioId);
}
