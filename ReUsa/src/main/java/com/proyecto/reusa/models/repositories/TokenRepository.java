package com.proyecto.reusa.models.repositories;

import com.proyecto.reusa.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

}
