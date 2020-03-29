package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Candidat;
import com.apbdoo.hrm.repository.CandidatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidatServiceImpl implements CandidatService {
    CandidatRepository candidatRepository;

    public CandidatServiceImpl(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

    @Override
    public List<Candidat> getCandidati() {
        List<Candidat> candidati = new ArrayList<>();
        candidatRepository.findAll().iterator().forEachRemaining(candidati::add);
        return candidati;
    }

    @Override
    public Candidat readCandidat(long idCandidat) {
        Candidat candidat = candidatRepository.findById(idCandidat).get();
        return candidat;
    }

    @Override
    public Candidat saveCandidat(Candidat candidat) {
        Candidat savedCandidat = candidatRepository.save(candidat);
        return savedCandidat;
    }

    @Override
    public void deleteCandidat(long idCandidat) {
        candidatRepository.deleteById(idCandidat);
    }
}
