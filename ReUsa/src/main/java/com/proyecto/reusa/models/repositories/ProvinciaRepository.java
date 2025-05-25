package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Provincia;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {

    Provincia getProvinciaByNombre(@Size(max = 50) @NotNull String nombre);
}
