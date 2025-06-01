package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Subcategoria;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubacategoriaRepository extends JpaRepository<Subcategoria, Integer> {
    Subcategoria getSubcategoriaById(Integer id);

    Subcategoria getSubcategoriaByNombre(@Size(max = 50) @NotNull String nombre);
}
