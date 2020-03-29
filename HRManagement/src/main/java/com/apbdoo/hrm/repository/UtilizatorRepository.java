package com.apbdoo.hrm.repository;

import com.apbdoo.hrm.entity.Utilizator;
import org.springframework.data.repository.CrudRepository;

public interface UtilizatorRepository extends CrudRepository<Utilizator, Long> {
    Utilizator findByUsername(String username);
}
