package com.example.vacantes.controller;

import com.example.vacantes.model.Vacante;
import com.example.vacantes.service.ICategoriasService;
import com.example.vacantes.service.IVacantesService;
import com.example.vacantes.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
    @Value("${empleosapp.ruta.imagenes} ")
    private String ruta;

    @Autowired
    private IVacantesService serviceVacantes;
    @Autowired
    private ICategoriasService serviceCategorias;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Vacante> lista = serviceVacantes.buscartodas();
        model.addAttribute("vacantes", lista);
        return "vacantes/listVacantes";
    }

    @GetMapping("/create")
    public String crear(Vacante vacante, Model model) {
        model.addAttribute("vacante", vacante);
        model.addAttribute("categorias", serviceCategorias.buscarTodas());
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Hay un error: " + error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }
        if (!multiPart.isEmpty()) {
            //String ruta = "c:/empleos/img-vacantes/"; @Value
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null) { // si sube la img, procesamos el nombreImagen
                vacante.setImagen(nombreImagen);
            }
        }
        serviceVacantes.guardar(vacante);
        attributes.addFlashAttribute("msm", "Registro guardado con éxito");
        System.out.println("nombre Vacante" + vacante);
        return "redirect:/vacantes/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = serviceVacantes.buscarPorId(idVacante);
        System.out.println("vacante: " + vacante);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes attributes, Model model) {
        serviceVacantes.eliminar(idVacante);
        attributes.addFlashAttribute("msm", "Registro eliminado de manera exitosa");
        System.out.println("Borrando vacante con id:" + idVacante);
        return "redirect:/vacantes/index";

    }

    @GetMapping("/editar/{id}")
    public String Editar(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = serviceVacantes.buscarPorId(idVacante);
        model.addAttribute("categorias", serviceCategorias.buscarTodas());
        model.addAttribute("vacante", vacante);
        return "vacantes/formVacante";

    }

    @GetMapping(value = "/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Vacante> lista = serviceVacantes.buscarTodas(page);
        model.addAttribute("vacantes", lista);
        return "vacantes/listVacantes";
    }

}

