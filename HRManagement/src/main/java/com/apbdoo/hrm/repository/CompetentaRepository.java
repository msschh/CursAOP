package com.apbdoo.hrm.repository;

import com.apbdoo.hrm.entity.Competenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CompetentaRepository extends JpaRepository<Competenta, Long> {
}
