package com.apbdoo.hrm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Evaluare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Acest camp trebuie introdus!")
    private String descriere;
    
    @ManyToOne
    private Angajat responsabil;
    
    @ManyToOne
    private Candidat candidat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Angajat getResponsabil() {
        return responsabil;
    }

    public void setResponsabil(Angajat responsabil) {
        this.responsabil = responsabil;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    @Override
    public String toString() {
        return "Evaluare{" +
                "id=" + id +
                ", descriere='" + descriere + '\'' +
                '}';
    }
}
