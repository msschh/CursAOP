package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Utilizator;
import com.apbdoo.hrm.repository.UtilizatorRepository;
import com.apbdoo.hrm.util.EncryptionUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilizatorServiceImpl implements UtilizatorService {
    UtilizatorRepository utilizatorRepository;

    public UtilizatorServiceImpl(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    @Override
    public List<Utilizator> getUtilizatori() {
        List<Utilizator> utilizatori = new ArrayList<>();
        utilizatorRepository.findAll().iterator().forEachRemaining(utilizatori::add);
        return utilizatori;
    }

    @Override
    public Utilizator readUtilizator(long idUtilizator) {
        Utilizator utilizator = utilizatorRepository.findById(idUtilizator).get();
        return utilizator;
    }

    @Override
    public Utilizator saveUtilizator(Utilizator utilizator) {
        if (utilizator.getId() == null) {
            utilizator.setParola(EncryptionUtil.encrypt(utilizator.getParola()));
        }
        Utilizator savedUtilizator = utilizatorRepository.save(utilizator);
        return savedUtilizator;
    }

    @Override
    public void deleteUtilizator(long idUtilizator) {
        utilizatorRepository.deleteById(idUtilizator);
    }

    @Override
    public Utilizator findByUsername(String username) {
        return utilizatorRepository.findByUsername(username);
    }

}
