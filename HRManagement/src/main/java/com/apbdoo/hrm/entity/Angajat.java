package com.apbdoo.hrm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Angajat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Acest camp trebuie introdus!")
    private String nume;
    @NotEmpty(message = "Acest camp trebuie introdus!")
    private String prenume;
    @NotEmpty(message = "Acest camp trebuie introdus!")
    @Size(min = 3, max = 5, message = "Codul trebuie sa aiba intre 3 si 5 caractere!")
    private String cod;
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private Date dtNastere;
    @Email(message = "Adresa de email introdusa nu este corecta!")
    private String email;
    @Size(max = 12, message = "Telefonul trebuie sa aiba maxim 12 caractere!")
    private String telefon;

    @ManyToOne
    private Functie functie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recruiter")
    private Set<Candidat> candidati = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsabil")
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

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Date getDtNastere() {
        return dtNastere;
    }

    public void setDtNastere(Date dtNastere) {
        this.dtNastere = dtNastere;
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

    public Functie getFunctie() {
        return functie;
    }

    public void setFunctie(Functie functie) {
        this.functie = functie;
    }

    public Set<Candidat> getCandidati() { return candidati; }

    public void setCandidati(Set<Candidat> candidati) {
        this.candidati = candidati;
    }

    public Set<Evaluare> getEvaluari() { return evaluari; }

    public void setEvaluari(Set<Evaluare> evaluari) { this.evaluari = evaluari; }

    public String getNumeComplet() { return this.prenume + " " + this.nume; }

    @Override
    public String toString() {
        return "Angajat{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", cod='" + cod + '\'' +
                ", dtNastere=" + dtNastere +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
