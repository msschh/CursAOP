package com.apbdoo.hrm.aspect;

import com.apbdoo.hrm.entity.Candidat;
import com.apbdoo.hrm.entity.Utilizator;
import com.apbdoo.hrm.service.CandidatService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LastViewsAspect {

    CandidatService candidatService;
    private HttpSession session;
    Map<String, List<Candidat>> candidats = new HashMap<>();

    public LastViewsAspect(CandidatService candidatService, HttpSession httpSession){
        this.session = httpSession;
        this.candidatService = candidatService;
    }

    @Before("execution(* com.apbdoo.hrm.controller.CandidatController.view(..))")
    public void addCandidatToMap(JoinPoint joinPoint) {
        Utilizator user = (Utilizator) session.getAttribute("userlogged");
        if (user == null) {
            return;
        }
        List<Candidat> candidatList = candidats.get(user.getUsername());
        Object[] args = joinPoint.getArgs();
        Candidat candidat = candidatService.readCandidat((long) args[0]);
        if (candidatList == null)
        {
            candidatList = new ArrayList<>();
        }
        if (candidatList.size() >= 3) {
            if (!candidatList.contains(candidat)) {
                candidatList.remove(0);
            }
        }
        candidatList.add(candidat);
        candidats.put(user.getUsername(), candidatList);
    }

    @After("execution(* com.apbdoo.hrm.controller.CandidatController.list(..))")
    public void addCandidatListToModel(JoinPoint joinPoint) {
        Utilizator user = (Utilizator) session.getAttribute("userlogged");
        if (user == null) {
            return;
        }
        List<Candidat> candidatList = candidats.get(user.getUsername());
        Object[] args = joinPoint.getArgs();
        Model model = (Model) args[0];
        model.addAttribute("viewCandidats", candidatList);
    }
}
