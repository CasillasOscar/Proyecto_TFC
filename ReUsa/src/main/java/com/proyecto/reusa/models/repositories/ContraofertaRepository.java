package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Contraoferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContraofertaRepository extends JpaRepository<Contraoferta, Integer> {
}
