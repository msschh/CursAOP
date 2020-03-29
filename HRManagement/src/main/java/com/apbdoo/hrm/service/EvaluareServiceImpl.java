package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Evaluare;
import com.apbdoo.hrm.repository.EvaluareRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluareServiceImpl implements EvaluareService {
    EvaluareRepository evaluareRepository;

    public EvaluareServiceImpl(EvaluareRepository evaluareRepository) {
        this.evaluareRepository = evaluareRepository;
    }

    @Override
    public List<Evaluare> getEvaluari() {
        List<Evaluare> evaluari = new ArrayList<>();
        evaluareRepository.findAll().iterator().forEachRemaining(evaluari::add);
        return evaluari;
    }

    @Override
    public Evaluare readEvaluare(long idEvaluare) {
        Evaluare evaluare = evaluareRepository.findById(idEvaluare).get();
        return evaluare;
    }

    @Override
    public Evaluare saveEvaluare(Evaluare evaluare) {
        Evaluare savedEvaluare = evaluareRepository.save(evaluare);
        return savedEvaluare;
    }

    @Override
    public void deleteEvaluare(long idEvaluare) {
        evaluareRepository.deleteById(idEvaluare);
    }
}
