package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Competenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetentaService {
    List<Competenta> getCompetente();

    Page<Competenta> getCompetente(Pageable pageable);

    Competenta readCompetenta(long idCompetenta);

    Competenta saveCompetenta(Competenta competenta);

    void deleteCompetenta(long idCompetenta);
}
