package com.example.vacantes.service;

import com.example.vacantes.model.Vacante;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class VacantesServiceImpl implements IVacantesService{
    private List<Vacante>lista =null;

    public VacantesServiceImpl(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        lista = new LinkedList<>();
        try {
            Vacante vacante1 = new Vacante();
            vacante1.setId(1);
            vacante1.setNombre("Ingeniero Civil");
            vacante1.setDescripcion("Experto en construcción de puentes");
            vacante1.setFecha(sdf.parse("08-05-2022"));
            vacante1.setSalario(4500.0);
            vacante1.setDestacado(1);
            vacante1.setImagen("logo15.png");


            Vacante vacante2 = new Vacante();
            vacante2.setId(2);
            vacante2.setNombre("Administrador");
            vacante2.setDescripcion("Experto en exportaciones");
            vacante2.setFecha(sdf.parse("09-04-2022"));
            vacante2.setSalario(3500.0);
            vacante2.setDestacado(0);
            vacante2.setImagen("logo2.png");

            Vacante vacante3 = new Vacante();
            vacante3.setId(3);
            vacante3.setNombre("Ingeniero Agroindustrial");
            vacante3.setDescripcion("Tener dos años de experiencia en el sector");
            vacante3.setFecha(sdf.parse("10-03-2022"));
            vacante3.setSalario(4000.0);
            vacante3.setDestacado(0);

            Vacante vacante4 = new Vacante();
            vacante4.setId(4);
            vacante4.setNombre("Diseñador");
            vacante4.setDescripcion("Experto en publicidad");
            vacante4.setFecha(sdf.parse("11-05-2022"));
            vacante4.setSalario(2500.0);
            vacante4.setDestacado(1);
            vacante4.setImagen("logo4.png");

            lista.add(vacante1);
            lista.add(vacante2);
            lista.add(vacante3);
            lista.add(vacante4);

        } catch (ParseException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    @Override
    public List<Vacante> buscartodas() {
        return lista;
    }

    @Override
    public Vacante buscarPorId(Integer idVacante) {

        for (Vacante v : lista) {
            if (v.getId() == idVacante) {
                return v;
            }
        }
        return null;

    }

    @Override
    public void guardar(Vacante vacante) {
        lista.add(vacante); // añade el obj vacante a la lista
    }

    @Override
    public List<Vacante> buscarDestacadas() {
        return null;
    }

    @Override
    public void eliminar(Integer idVacante) {

    }

    @Override
    public List<Vacante> buscarByExample(Example<Vacante> example) {
        return null;
    }

    @Override
    public Page<Vacante> buscarTodas(Pageable page) {
        return null;
    }

}

