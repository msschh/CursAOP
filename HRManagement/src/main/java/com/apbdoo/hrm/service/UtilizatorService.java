package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Utilizator;

import java.util.List;

public interface UtilizatorService {

    List<Utilizator> getUtilizatori();

    Utilizator readUtilizator(long idUtilizator);

    Utilizator saveUtilizator(Utilizator utilizator);

    void deleteUtilizator(long idUtilizator);

    Utilizator findByUsername(String username);
}
