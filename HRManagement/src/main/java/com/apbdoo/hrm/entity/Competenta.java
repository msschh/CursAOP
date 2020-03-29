package com.apbdoo.hrm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Competenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Acest camp trebuie introdus!")
    private String nume;

    @NotEmpty(message = "Acest camp trebuie introdus!")
    private String descriere;

    @ManyToMany(mappedBy = "competente")
    private Set<Candidat> candidati = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Set<Candidat> getCandidati() { return candidati; }

    public void setCandidati(Set<Candidat> candidati) { this.candidati = candidati; }

    @Override
    public String toString() {
        return "Competenta{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }
}
