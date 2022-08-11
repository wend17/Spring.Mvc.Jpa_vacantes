package com.example.vacantes.controller;

import com.example.vacantes.model.Vacante;
import com.example.vacantes.service.IVacantesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IVacantesService serviceVacantes;

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        List<Vacante> lista = serviceVacantes.buscartodas(); // el metodo retorna una lista, variable de tipo lista, la lista la obtenemos de serviceVacantes con el método
        model.addAttribute("vacantes", lista);
        return "tabla";
    }

    @GetMapping("/detalle")
    public String mostrarDestalle(Model model) {
        Vacante vacante = new Vacante(); // variable tipo objeto
        vacante.setNombre("Ingeniero de Comunicaciones");
        vacante.setDescripcion("Se solicita ingeniero para dar soporte");
        vacante.setFecha(new Date());
        vacante.setSalario(9500.0);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }
    @GetMapping("/listado")
    public String MostrarListado(Model model) {
        List<String> lista = new LinkedList<>(); // variable lista enlazada
        lista.add("Ingeniero de Sistemas");
        lista.add("Auxiliar de Contabilidad");
        lista.add("Vendedor");
        lista.add("Arquitecta");
        model.addAttribute("empleos", lista);
        return "listado";
    }
    @GetMapping("/")
    public String mostrarHome(Model model) {
        String nombre = "Auxiliar de Contabilidad";
        Date fechaPub = new Date();
        double salario = 9000.0;
        boolean vigente = true;
        model.addAttribute("nombre", nombre);
        model.addAttribute("fecha", new Date());
        model.addAttribute("salario", salario);
        model.addAttribute("vigente", vigente);
        return "home";
    }


}


