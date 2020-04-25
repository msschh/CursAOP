package com.apbdoo.hrm.controller;

import com.apbdoo.hrm.entity.Evaluare;
import com.apbdoo.hrm.entity.Utilizator;
import com.apbdoo.hrm.service.UtilizatorService;
import com.apbdoo.hrm.util.EncryptionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    UtilizatorService utilizatorService;

    public LoginController(UtilizatorService utilizatorService) {
        this.utilizatorService = utilizatorService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("utilizator", new Utilizator());
        return "/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Utilizator utilizator, HttpServletRequest request, BindingResult result, Model model) {
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "username", null, "Username nu poate fi vid");
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "parola", null, "Parola nu poate fi vida");
        if (result.hasErrors()) {
            model.addAttribute("utilizator", new Utilizator());
            return "login";
        }

        Utilizator userDb = utilizatorService.findByUsername(utilizator.getUsername());
        if (userDb == null) {
            result.rejectValue("username", null, "Nu exista utilizatorul");
            model.addAttribute("utilizator", new Utilizator());
            return "login";
        }

        if (userDb.getParola().equals(EncryptionUtil.encrypt(utilizator.getParola()))) {
            request.getSession().setAttribute("userlogged", userDb);
            return "/index";
        } else {
            result.rejectValue("parola", null, "Parola incorecta");
            model.addAttribute("utilizator", new Utilizator());
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout (HttpServletRequest request) {
        request.getSession().setAttribute("userlogged", null);
        return "/index";
    }
}
