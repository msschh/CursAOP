package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Utilizator;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UtilizatorService extends UserDetailsService {

    List<Utilizator> getUtilizatori();

    Utilizator readUtilizator(long idUtilizator);

    Utilizator saveUtilizator(Utilizator utilizator);

    void deleteUtilizator(long idUtilizator);

    Utilizator findByUsername(String username);
}
