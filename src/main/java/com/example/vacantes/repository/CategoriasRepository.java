package com.example.vacantes.repository;


import com.example.vacantes.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
}
