package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Angajat;

import java.util.List;

public interface AngajatService {
    List<Angajat> getAngajati();

    Angajat readAngajat(long idAngajat);

    Angajat saveAngajat(Angajat angajat);

    void deleteAngajat(long idAngajat);

    List<Angajat> getRecruiteri();

    List<Angajat> getProjectManageri();
}
