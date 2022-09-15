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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/")
    public String mostrarHome(Model model) {
        model.addAttribute("categorias", serviceCategorias.buscarTodas());
        model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
        return "home";
    }

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        model.addAttribute("vacantes", serviceVacantes.buscarTodas());
        return "tabla";
    }

    @GetMapping(value = "/tabla/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Vacante> lista = serviceVacantes.buscarTodas(page);
        model.addAttribute("vacantes", lista);
        return "tabla";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes attributes, Model model) {
        serviceVacantes.eliminar(idVacante);
        attributes.addFlashAttribute("msm", "Registro eliminado de manera exitosa");
        System.out.println("Borrando vacante con id:" + idVacante);
        return "redirect:/tabla/indexPaginate";

    }

    @ModelAttribute
    public void setGenericos(Model model) {
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


}



