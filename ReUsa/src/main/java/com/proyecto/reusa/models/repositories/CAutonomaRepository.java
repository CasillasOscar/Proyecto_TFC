package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.ComunidadAutonoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CAutonomaRepository extends JpaRepository<ComunidadAutonoma, Integer> {
}
