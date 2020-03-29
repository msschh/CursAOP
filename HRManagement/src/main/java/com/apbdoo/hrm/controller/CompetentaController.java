package com.apbdoo.hrm.controller;

import com.apbdoo.hrm.entity.Competenta;
import com.apbdoo.hrm.entity.Functie;
import com.apbdoo.hrm.service.CompetentaService;
import com.apbdoo.hrm.service.FunctieService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CompetentaController {
    CompetentaService competentaService;

    public CompetentaController(CompetentaService competentaService) {
        this.competentaService = competentaService;
    }

    @GetMapping("competenta")
    public String list(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("page", competentaService.getCompetente(pageable));
        return "competenta/list";
    }

    @GetMapping("competenta/view/{idCompetenta}")
    public String view(@PathVariable long idCompetenta, Model model) {
        model.addAttribute("competenta", competentaService.readCompetenta(idCompetenta));
        return "competenta/view";
    }

    @GetMapping("competenta/add")
    public String add(Model model) {
        model.addAttribute("competenta", new Competenta());
        return "competenta/add";
    }

    @GetMapping("competenta/edit/{idCompetenta}")
    public String edit(@PathVariable long idCompetenta, Model model) {
        model.addAttribute("competenta", competentaService.readCompetenta(idCompetenta));
        return "competenta/add";
    }

    @PostMapping("competenta/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute Competenta competenta, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "competenta/add";
        }
        Competenta saveCompetenta = competentaService.saveCompetenta(competenta);
        return "redirect:/competenta/view/" + saveCompetenta.getId();
    }

    @GetMapping("competenta/delete/{idCompetenta}")
    public String delete(@PathVariable long idCompetenta) {
        competentaService.deleteCompetenta(idCompetenta);
        return "redirect:/competenta";
    }
}
