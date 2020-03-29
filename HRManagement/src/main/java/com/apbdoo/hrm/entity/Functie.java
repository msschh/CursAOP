package com.apbdoo.hrm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Functie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Acest camp trebuie introdus!")
    private String nume;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "functie")
    private Set<Angajat> angajati = new HashSet<>();

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

    public Set<Angajat> getAngajati() {
        return angajati;
    }

    public void setAngajati(Set<Angajat> angajati) {
        this.angajati = angajati;
    }

    @Override
    public String toString() {
        return "Functie{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }
}