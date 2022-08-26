package com.example.vacantes.service;

import com.example.vacantes.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriasServiceImpl implements ICategoriasService {

    private List<Categoria> lista = null; // guarda las categorias en una lista

    public CategoriasServiceImpl() {
    lista = new LinkedList<>();

        Categoria cat1 = new Categoria();
        cat1.setId(1);
        cat1.setNombre("Ingeniería");
        cat1.setDescripcion("Experiencia en procesos textiles");


        Categoria cat2 = new Categoria();
        cat2.setId(2);
        cat2.setNombre("Ventas");
        cat2.setDescripcion("Experiencia en ventas");



        Categoria cat3 = new Categoria();
        cat3.setId(3);
        cat3.setNombre("Comunicaciones");
        cat3.setDescripcion("Experiencia en comunicaciones");


        Categoria cat4 = new Categoria();
        cat4.setId(4);
        cat4.setNombre("Logística");
        cat4.setDescripcion("Experiencia en almacenes");

        Categoria cat5 = new Categoria();
        cat5.setId(5);
        cat5.setNombre("Diseño");
        cat5.setDescripcion("Experiencia en diseño de interiores");

        lista.add(cat1);
        lista.add(cat2);
        lista.add(cat3);
        lista.add(cat4);
        lista.add(cat5);
    }

    @Override
    public void guardar(Categoria categoria) {
    lista.add(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return lista;
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        for (Categoria c : lista){
            if(c.getId()==idCategoria){
                return c;
            }
        }
        return null;
    }

    @Override
    public void eliminar(Integer idCategoria) {

    }

    @Override
    public Page<Categoria> buscarTodas(Pageable page) {
        return null;
    }
}




