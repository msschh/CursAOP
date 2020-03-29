package com.apbdoo.hrm.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Acest camp trebuie introdus!")
    private String nume;
    @NotEmpty(message = "Acest camp trebuie introdus!")
    private String prenume;
    @Email(message = "Adresa de email introdusa nu este corecta!")
    private String email;
    @Size(max = 12, message = "Telefonul trebuie sa aiba maxim 12 caractere!")
    private String telefon;

    @ManyToOne
    private Angajat recruiter;

    @ManyToMany
    @JoinTable(name = "candidat_competenta",
            joinColumns = @JoinColumn(name = "candidat_id"),
            inverseJoinColumns = @JoinColumn(name = "competenta_id"))
    private Set<Competenta> competente = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidat")
    private Set<Evaluare> evaluari = new HashSet<>();

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

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Angajat getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Angajat recruiter) {
        this.recruiter = recruiter;
    }

    public Set<Competenta> getCompetente() {
        return competente;
    }

    public void setCompetente(Set<Competenta> competente) { this.competente = competente; }

    public Set<Evaluare> getEvaluari() { return evaluari; }

    public void setEvaluari(Set<Evaluare> evaluari) { this.evaluari = evaluari; }

    public String getNumeComplet() { return this.prenume + " " + this.nume; }

    @Override
    public String toString() {
        return "Candidat{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
