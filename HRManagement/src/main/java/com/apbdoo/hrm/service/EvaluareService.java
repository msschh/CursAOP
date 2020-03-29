package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Evaluare;

import java.util.List;

public interface EvaluareService {
    List<Evaluare> getEvaluari();
    
    Evaluare readEvaluare(long idEvaluare);
    
    Evaluare saveEvaluare(Evaluare evaluare);
    
    void deleteEvaluare(long idEvaluare);
}
