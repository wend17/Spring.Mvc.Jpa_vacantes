package com.example.vacantes.service;

import com.example.vacantes.model.Vacante;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class VacantesServiceImpl implements IVacantesService{
    private List<Vacante>lista =null; //2.declaro la lista como atributo, para q la lista este disponible en cualquier metodo dentro de esta clase

    public VacantesServiceImpl(){  // 1.creamos un constructor
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        lista = new LinkedList<>(); // para estar disponible en cualquier método de la clase de servicio
        try {
            Vacante vacante1 = new Vacante();
            vacante1.setId(1);
            vacante1.setNombre("Ingeniero Civil");
            vacante1.setDescripcion("Se solicita ingeniero para puente peatonal.");
            vacante1.setFecha(sdf.parse("08-02-2022"));
            vacante1.setSalario(14500.0);
            vacante1.setDestacado(1);
            vacante1.setImagen("logo1.png");

            Vacante vacante2 = new Vacante();
            vacante2.setId(2);
            vacante2.setNombre("Ingeniero Ambiental");
            vacante2.setDescripcion("Se solicita ingeniero para trabajar en provincia.");
            vacante2.setFecha(sdf.parse("09-02-2022"));
            vacante2.setSalario(12000.0);
            vacante2.setDestacado(0);
            vacante2.setImagen("logo2.png");

            Vacante vacante3 = new Vacante();
            vacante3.setId(3);
            vacante3.setNombre("Ingeniero Textil");
            vacante3.setDescripcion("Se solicita ingeniero para control de calidad de telas.");
            vacante3.setFecha(sdf.parse("10-02-2022"));
            vacante3.setSalario(12000.0);
            vacante3.setDestacado(0);

            Vacante vacante4 = new Vacante();
            vacante4.setId(4);
            vacante4.setNombre("Diseñador de Interiores");
            vacante4.setDescripcion("Se solicita diseñador para trabajo a medio tiempo.");
            vacante4.setFecha(sdf.parse("11-02-2022"));
            vacante4.setSalario(7500.0);
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
        return lista; // implementación del punto 2 ya q lista esta declarado como atributo  privado
    }

    @Override
    public Vacante buscarPorId(Integer idvacante) {

        for (Vacante v : lista) {
            if (v.getId() == idvacante) {
                return v;
            }
        }
        return null;

    }

}

