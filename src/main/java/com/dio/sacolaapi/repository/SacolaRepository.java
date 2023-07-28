package com.dio.sacolaapi.repository;

import com.dio.sacolaapi.model.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, UUID>{
}
