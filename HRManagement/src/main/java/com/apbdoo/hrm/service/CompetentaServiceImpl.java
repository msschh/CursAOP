package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Competenta;
import com.apbdoo.hrm.repository.CompetentaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetentaServiceImpl implements CompetentaService {
    CompetentaRepository competentaRepository;

    public CompetentaServiceImpl(CompetentaRepository competentaRepository) {
        this.competentaRepository = competentaRepository;
    }

    @Override
    public List<Competenta> getCompetente() {
        List<Competenta> competente = new ArrayList<>();
        competentaRepository.findAll().iterator().forEachRemaining(competente::add);
        return competente;
    }

    @Override
    public Page<Competenta> getCompetente(Pageable pageable) {
        Page<Competenta> competente = competentaRepository.findAll(pageable);
        return competente;
    }

    @Override
    public Competenta readCompetenta(long idCompetenta) {
        Competenta competenta = competentaRepository.findById(idCompetenta).get();
        return competenta;
    }

    @Override
    public Competenta saveCompetenta(Competenta competenta) {
        Competenta savedCompetenta = competentaRepository.save(competenta);
        return savedCompetenta;
    }

    @Override
    public void deleteCompetenta(long idCompetenta) {
        competentaRepository.deleteById(idCompetenta);
    }
}
