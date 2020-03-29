package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Candidat;

import java.util.List;

public interface CandidatService {
    List<Candidat> getCandidati();

    Candidat readCandidat(long idCandidat);

    Candidat saveCandidat(Candidat candidat);

    void deleteCandidat(long idCandidat);
}
