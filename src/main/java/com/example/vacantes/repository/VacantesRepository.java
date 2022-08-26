package com.example.vacantes.repository;


import com.example.vacantes.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacantesRepository extends JpaRepository<Vacante,Integer> {

    List<Vacante>findByDestacadoAndEstatusOrderByIdDesc(int destacado,String estatus);





}
