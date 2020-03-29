package com.apbdoo.hrm.controller;

import com.apbdoo.hrm.entity.Evaluare;
import com.apbdoo.hrm.entity.Utilizator;
import com.apbdoo.hrm.service.AngajatService;
import com.apbdoo.hrm.service.CandidatService;
import com.apbdoo.hrm.service.EvaluareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class EvaluareController {
    EvaluareService evaluareService;
    CandidatService candidatService;
    AngajatService angajatService;

    public EvaluareController(EvaluareService evaluareService, CandidatService candidatService, AngajatService angajatService) {
        this.evaluareService = evaluareService;
        this.candidatService = candidatService;
        this.angajatService = angajatService;
    }

    @GetMapping("evaluare")
    public String list(Model model) {
        model.addAttribute("evaluari", evaluareService.getEvaluari());
        return "evaluare/list";
    }

    @GetMapping("evaluare/view/{idEvaluare}")
    public String view(@PathVariable long idEvaluare, Model model) {
        model.addAttribute("evaluare", evaluareService.readEvaluare(idEvaluare));
        return "evaluare/view";
    }

    @GetMapping("evaluare/add")
    public String add(Model model) {
        model.addAttribute("evaluare", new Evaluare());
        model.addAttribute("responsabili", angajatService.getProjectManageri());
        model.addAttribute("candidati", candidatService.getCandidati());
        return "evaluare/add";
    }

    @GetMapping("evaluare/edit/{idEvaluare}")
    public String edit(@PathVariable long idEvaluare, Model model) {
        model.addAttribute("evaluare", evaluareService.readEvaluare(idEvaluare));
        model.addAttribute("responsabili", angajatService.getProjectManageri());
        model.addAttribute("candidati", candidatService.getCandidati());
        return "evaluare/add";
    }

    @PostMapping("evaluare/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute Evaluare evaluare, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("responsabili", angajatService.getProjectManageri());
            model.addAttribute("candidati", candidatService.getCandidati());
            return "evaluare/add";
        }
        Evaluare savedEvaluare = evaluareService.saveEvaluare(evaluare);
        return "redirect:/evaluare/view/" + evaluare.getId();
    }

    @GetMapping("evaluare/delete/{idEvaluare}")
    public String delete(@PathVariable long idEvaluare) {
        evaluareService.deleteEvaluare(idEvaluare);
        return "redirect:/evaluare";
    }
}
