package com.apbdoo.hrm.controller;

import com.apbdoo.hrm.entity.Utilizator;
import com.apbdoo.hrm.service.AngajatService;
import com.apbdoo.hrm.service.RoleService;
import com.apbdoo.hrm.service.UtilizatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UtilizatorController {
    UtilizatorService utilizatorService;
    AngajatService angajatService;
    RoleService roleService;

    public UtilizatorController(UtilizatorService utilizatorService, AngajatService angajatService, RoleService roleService) {
        this.utilizatorService = utilizatorService;
        this.angajatService = angajatService;
        this.roleService = roleService;
    }

    @GetMapping("utilizator")
    public String list(Model model) {
        model.addAttribute("utilizatori", utilizatorService.getUtilizatori());
        return "utilizator/list";
    }

    @GetMapping("utilizator/view/{idUtilizator}")
    public String view(@PathVariable long idUtilizator, Model model) {
        model.addAttribute("utilizator", utilizatorService.readUtilizator(idUtilizator));
        model.addAttribute("roles", roleService.getRoles());
        return "utilizator/view";
    }

    @GetMapping("utilizator/add")
    public String add(Model model) {
        model.addAttribute("utilizator", new Utilizator());
        model.addAttribute("angajati", angajatService.getAngajati());
        model.addAttribute("roles", roleService.getRoles());
        return "utilizator/add";
    }

    @GetMapping("utilizator/edit/{idUtilizator}")
    public String edit(@PathVariable long idUtilizator, Model model) {
        model.addAttribute("utilizator", utilizatorService.readUtilizator(idUtilizator));
        model.addAttribute("angajati", angajatService.getAngajati());
        model.addAttribute("roles", roleService.getRoles());
        return "utilizator/add";
    }

    @PostMapping("utilizator/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute Utilizator utilizator, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("angajati", angajatService.getAngajati());
            model.addAttribute("roles", roleService.getRoles());
            return "utilizator/add";
        }
        Utilizator savedUtilizator = utilizatorService.saveUtilizator(utilizator);
        return "redirect:/utilizator/view/" + savedUtilizator.getId();
    }

    @GetMapping("utilizator/delete/{idUtilizator}")
    public String delete(@PathVariable long idUtilizator) {
        utilizatorService.deleteUtilizator(idUtilizator);
        return "redirect:/utilizator";
    }
}
