package com.example.vacantes.controller;

import com.example.vacantes.model.Categoria;

import com.example.vacantes.service.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private ICategoriasService serviceCategorias;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Categoria> list = serviceCategorias.buscarTodas();
        model.addAttribute("categorias", list);
        return "categorias/listCategorias";// ruta de la vista
    }

    @GetMapping("/create")
    public String crear(Categoria categoria, Model model) {
        model.addAttribute("categoria", categoria);
        return "categorias/formCategorias";
    }

    @PostMapping("/save")
    public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrió un error: " + error.getDefaultMessage());
            }
            return "categorias/formCategorias";
        }
        serviceCategorias.guardar(categoria);
        attributes.addFlashAttribute("msm", "Registro guardado con éxito");
        System.out.println("categoria" + categoria);
        return "redirect:/categorias/index"; // ruta de la vista x el método
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attributes,Model model) {
        serviceCategorias.eliminar(idCategoria);
        attributes.addFlashAttribute("msm","Registro eliminado de manera exitosa");
        return "redirect:/categorias/index";
    }
    @GetMapping("/editar/{id}")
    public String Editar(@PathVariable("id") int idVacante, Model model) {
        Categoria categoria = serviceCategorias.buscarPorId(idVacante);
        model.addAttribute("categoria", categoria);
        return "categorias/formCategorias";

    }

    @GetMapping(value = "/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Categoria> lista = serviceCategorias.buscarTodas(page);
        model.addAttribute("categorias", lista);
        return "categorias/listCategorias";
    }
}



