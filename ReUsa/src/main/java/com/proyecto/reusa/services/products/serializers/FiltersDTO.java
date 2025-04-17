package com.proyecto.reusa.services.products.serializers;

public record FiltersDTO(
        String categoria,
        String subcategoria,
        Integer maxPrecio,
        Integer minPrecio,
        String provincia,
        String CCAA,
        String estado
) {}
