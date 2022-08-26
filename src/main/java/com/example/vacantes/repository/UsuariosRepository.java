package com.example.vacantes.repository;


import com.example.vacantes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario,Integer> {
}
