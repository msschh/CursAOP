package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Angajat;
import com.apbdoo.hrm.repository.AngajatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AngajatServiceImpl implements AngajatService {
    private static final Long ID_RECRUITER = 106l;
    private static final Long ID_PROJECT_MANAGER = 108l;

    AngajatRepository angajatRepository;

    public AngajatServiceImpl(AngajatRepository angajatRepository) {
        this.angajatRepository = angajatRepository;
    }

    @Override
    public List<Angajat> getAngajati() {
        List<Angajat> angajati = new ArrayList<>();
        angajatRepository.findAll().iterator().forEachRemaining(angajati::add);
        return angajati;
    }

    @Override
    public Angajat readAngajat(long idAngajat) {
        Angajat angajat = angajatRepository.findById(idAngajat).get();
        return angajat;
    }

    @Override
    public Angajat saveAngajat(Angajat angajat) {
        Angajat savedAngajat = angajatRepository.save(angajat);
        return savedAngajat;
    }

    @Override
    public void deleteAngajat(long idAngajat) {
        angajatRepository.deleteById(idAngajat);
    }

    @Override
    public List<Angajat> getRecruiteri() {
        List<Angajat> recruiteri = new ArrayList<>();
        getAngajati().stream().filter(angajat -> angajat.getFunctie().getId().equals(ID_RECRUITER)).forEach(recruiteri::add);
        return recruiteri;
    }

    @Override
    public List<Angajat> getProjectManageri() {
        List<Angajat> projectManageri = new ArrayList<>();
        getAngajati().stream().filter(angajat -> angajat.getFunctie().getId().equals(ID_PROJECT_MANAGER)).forEach(projectManageri::add);
        return projectManageri;
    }

}
