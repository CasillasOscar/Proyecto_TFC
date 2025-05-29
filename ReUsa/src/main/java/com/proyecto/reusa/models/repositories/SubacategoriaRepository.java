package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubacategoriaRepository extends JpaRepository<Subcategoria, Integer> {
    Subcategoria getSubcategoriaById(Integer id);
}
