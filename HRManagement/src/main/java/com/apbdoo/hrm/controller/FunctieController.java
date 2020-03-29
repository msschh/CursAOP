package com.apbdoo.hrm.controller;

import com.apbdoo.hrm.entity.Functie;
import com.apbdoo.hrm.service.FunctieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class FunctieController {
    FunctieService functieService;

    public FunctieController(FunctieService functieService) {
        this.functieService = functieService;
    }

    @GetMapping("functie")
    public String list(Model model) {
        model.addAttribute("functii", functieService.getFunctii());
        return "functie/list";
    }

    @GetMapping("functie/view/{idFunctie}")
    public String view(@PathVariable long idFunctie, Model model) {
        model.addAttribute("functie", functieService.readFunctie(idFunctie));
        return "functie/view";
    }

    @GetMapping("functie/add")
    public String add(Model model) {
        model.addAttribute("functie", new Functie());
        return "functie/add";
    }

    @GetMapping("functie/edit/{idFunctie}")
    public String edit(@PathVariable long idFunctie, Model model) {
        model.addAttribute("functie", functieService.readFunctie(idFunctie));
        return "functie/add";
    }

    @PostMapping("functie/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute Functie functie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "functie/add";
        }
        Functie savedFunctie = functieService.saveFunctie(functie);
        return "redirect:/functie/view/" + savedFunctie.getId();
    }

    @GetMapping("functie/delete/{idFunctie}")
    public String delete(@PathVariable long idFunctie) {
        functieService.deleteFunctie(idFunctie);
        return "redirect:/functie";
    }
}
