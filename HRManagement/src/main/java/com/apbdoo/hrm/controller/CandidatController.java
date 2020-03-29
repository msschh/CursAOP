package com.apbdoo.hrm.controller;

import com.apbdoo.hrm.entity.Candidat;
import com.apbdoo.hrm.service.AngajatService;
import com.apbdoo.hrm.service.CandidatService;
import com.apbdoo.hrm.service.CompetentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CandidatController {
    CandidatService candidatService;
    AngajatService angajatService;
    CompetentaService competentaService;

    public CandidatController(CandidatService candidatService, AngajatService angajatService, CompetentaService competentaService) {
        this.candidatService = candidatService;
        this.angajatService = angajatService;
        this.competentaService = competentaService;
    }

    @GetMapping("candidat")
    public String list(Model model) {
        model.addAttribute("candidati", candidatService.getCandidati());
        return "candidat/list";
    }

    @GetMapping("candidat/view/{idCandidat}")
    public String view(@PathVariable long idCandidat, Model model) {
        model.addAttribute("candidat", candidatService.readCandidat(idCandidat));
        model.addAttribute("competente", competentaService.getCompetente());
        return "candidat/view";
    }

    @GetMapping("candidat/add")
    public String add(Model model) {
        model.addAttribute("candidat", new Candidat());
        model.addAttribute("recruiteri", angajatService.getRecruiteri());
        model.addAttribute("competente", competentaService.getCompetente());
        return "candidat/add";
    }

    @GetMapping("candidat/edit/{idCandidat}")
    public String edit(@PathVariable long idCandidat, Model model) {
        model.addAttribute("candidat", candidatService.readCandidat(idCandidat));
        model.addAttribute("recruiteri", angajatService.getRecruiteri());
        model.addAttribute("competente", competentaService.getCompetente());
        return "candidat/add";
    }

    @PostMapping("candidat/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute Candidat candidat, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("recruiteri", angajatService.getRecruiteri());
            model.addAttribute("competente", competentaService.getCompetente());
            return "candidat/add";
        }
        Candidat savedCandidat = candidatService.saveCandidat(candidat);
        return "redirect:/candidat/view/" + savedCandidat.getId();
    }

    @GetMapping("candidat/delete/{idCandidat}")
    public String delete(@PathVariable long idCandidat) {
        candidatService.deleteCandidat(idCandidat);
        return "redirect:/candidat";
    }
}
