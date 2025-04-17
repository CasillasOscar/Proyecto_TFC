package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
