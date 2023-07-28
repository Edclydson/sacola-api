package com.dio.sacolaapi.repository;

import com.dio.sacolaapi.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, UUID>{
}
