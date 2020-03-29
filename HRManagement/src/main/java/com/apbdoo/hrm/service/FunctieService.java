package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Functie;

import java.util.List;

public interface FunctieService {

    List<Functie> getFunctii();

    Functie readFunctie(long idFunctie);

    Functie saveFunctie(Functie functie);

    void deleteFunctie(long idFunctie);
}
