package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> getVentasByIdUsuarioComprador_Id(Integer idUsuarioCompradorId);

    List<Venta> getVentasByIdUsuarioVendedor_Id(Integer idUsuarioVendedorId);
}
