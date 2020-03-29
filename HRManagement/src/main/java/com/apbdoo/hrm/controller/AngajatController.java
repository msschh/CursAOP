package com.apbdoo.hrm.controller;

import com.apbdoo.hrm.entity.Angajat;
import com.apbdoo.hrm.service.AngajatService;
import com.apbdoo.hrm.service.FunctieService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AngajatController {
    AngajatService angajatService;
    FunctieService functieService;

    public AngajatController(AngajatService angajatService, FunctieService functieService) {
        this.angajatService = angajatService;
        this.functieService = functieService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("angajat")
    public String list(Model model) {
        model.addAttribute("angajati", angajatService.getAngajati());
        return "angajat/list";
    }

    @GetMapping("angajat/view/{idAngajat}")
    public String view(@PathVariable long idAngajat, Model model) {
        model.addAttribute("angajat", angajatService.readAngajat(idAngajat));
        return "angajat/view";
    }

    @GetMapping("angajat/add")
    public String add(Model model) {
        model.addAttribute("angajat", new Angajat());
        model.addAttribute("functii", functieService.getFunctii());
        return "angajat/add";
    }

    @GetMapping("angajat/edit/{idAngajat}")
    public String edit(@PathVariable long idAngajat, Model model) {
        model.addAttribute("angajat", angajatService.readAngajat(idAngajat));
        model.addAttribute("functii", functieService.getFunctii());
        return "angajat/add";
    }

    @PostMapping("angajat/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute Angajat angajat, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("functii", functieService.getFunctii());
            return "angajat/add";
        }
        Angajat savedAngajat = angajatService.saveAngajat(angajat);
        return "redirect:/angajat/view/" + savedAngajat.getId();
    }

    @GetMapping("angajat/delete/{idAngajat}")
    public String delete(@PathVariable long idAngajat) {
        angajatService.deleteAngajat(idAngajat);
        return "redirect:/angajat";
    }
}
