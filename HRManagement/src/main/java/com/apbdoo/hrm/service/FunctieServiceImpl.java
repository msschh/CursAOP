package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Functie;
import com.apbdoo.hrm.repository.FunctieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FunctieServiceImpl implements FunctieService {
    FunctieRepository functieRepository;

    public FunctieServiceImpl(FunctieRepository functieRepository) {
        this.functieRepository = functieRepository;
    }

    @Override
    public List<Functie> getFunctii() {
        List<Functie> functii = new ArrayList<>();
        functieRepository.findAll().iterator().forEachRemaining(functii::add);
        return functii;
    }

    @Override
    public Functie readFunctie(long idFunctie) {
        Functie functie = functieRepository.findById(idFunctie).get();
        return functie;
    }

    @Override
    public Functie saveFunctie(Functie functie) {
        Functie savedFunctie = functieRepository.save(functie);
        return savedFunctie;
    }

    @Override
    public void deleteFunctie(long idFunctie) {
        functieRepository.deleteById(idFunctie);
    }
}
