package com.apbdoo.hrm.aspect;

import com.apbdoo.hrm.entity.Role;
import com.apbdoo.hrm.entity.Utilizator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
@Slf4j
public class SecurityAspect {

    private static Role ROLE_HR = new Role("ROLE_HR");
    private static Role ROLE_CANDIDAT = new Role("ROLE_CANDIDAT");
    private static Role ROLE_ADMIN = new Role("ROLE_ADMIN");

    private HttpSession session;

    public SecurityAspect(HttpSession httpSession){
        this.session = httpSession;
    }

    @Around("execution(* com.apbdoo.hrm.controller.AngajatController.*(..)) || " +
            "execution(* com.apbdoo.hrm.controller.FunctieController.*(..))")
    public Object verifyAccessHR(ProceedingJoinPoint pjp) throws Throwable {
        return verifyAccess(pjp, ROLE_HR);
    }

    @Around("execution(* com.apbdoo.hrm.controller.CandidatController.*(..)) || " +
            "execution(* com.apbdoo.hrm.controller.CompetentaController.*(..)) || " +
            "execution(* com.apbdoo.hrm.controller.EvaluareController.*(..))")
    public Object verifyAccessCandidat(ProceedingJoinPoint pjp) throws Throwable {
        return verifyAccess(pjp, ROLE_CANDIDAT);
    }

    @Around("execution(* com.apbdoo.hrm.controller.UtilizatorController.*(..))")
    public Object verifyAccessAdmin(ProceedingJoinPoint pjp) throws Throwable {
        return verifyAccess(pjp, ROLE_ADMIN);
    }

    private Object verifyAccess(ProceedingJoinPoint pjp, Role role) throws Throwable {
        Utilizator user = (Utilizator) session.getAttribute("userlogged");
        if (user == null) {
            return "/index";
        }
        if (user.getRoles().contains(role)) {
            return pjp.proceed();
        }
        return "/notauthorized";
    }
}
