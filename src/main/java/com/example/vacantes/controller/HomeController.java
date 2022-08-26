package com.example.vacantes.controller;


import com.example.vacantes.model.Vacante;
import com.example.vacantes.service.ICategoriasService;
import com.example.vacantes.service.IVacantesService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IVacantesService serviceVacantes;

    @Autowired
    private ICategoriasService serviceCategorias;

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        List<Vacante> lista = serviceVacantes.buscartodas();
        model.addAttribute("vacantes", lista);
        return "tabla";
    }

    @GetMapping("/")
    public String mostrarHome(Model model) {
        model.addAttribute("categorias", serviceCategorias.buscarTodas());
        return "home";
    }

    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
        Vacante vacanteSearch = new Vacante();
        vacanteSearch.reset();
        model.addAttribute("search1", vacanteSearch);

    }

    @GetMapping("/search")
    public String buscar(@ModelAttribute("search1") Vacante vacante, Model model) { // equivalente a los 2 Request

        ExampleMatcher matcher = ExampleMatcher.matching().
                withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Vacante> example = Example.of(vacante, matcher);
        List<Vacante> lista = serviceVacantes.buscarByExample(example);
        model.addAttribute("vacantes", lista);
        model.addAttribute("categorias", serviceCategorias.buscarTodas());
        return "home";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping(value = "/tabla/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Vacante> lista = serviceVacantes.buscarTodas(page);
        model.addAttribute("vacantes", lista);
        return "tabla";
    }


}



