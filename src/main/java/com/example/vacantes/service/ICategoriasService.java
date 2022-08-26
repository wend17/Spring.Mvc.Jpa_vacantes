package com.example.vacantes.service;

import com.example.vacantes.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriasService {
    void guardar (Categoria categoria);
    List<Categoria> buscarTodas();
    Categoria buscarPorId(Integer idCategoria);
    void eliminar (Integer idCategoria);
    public Page<Categoria> buscarTodas(Pageable page);
}
