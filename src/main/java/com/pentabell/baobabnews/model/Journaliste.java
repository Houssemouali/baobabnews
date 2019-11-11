package com.pentabell.baobabnews.model;

import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="journaliste")
@NaturalIdCache
public class Journaliste extends Users {

    @Column(name="Journalist_Id",unique = true, nullable = false)
    private int id;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})")
    @Column(name = "numtel")
    private long numtel;

    @NotNull
    @NotEmpty
    @Column(name = "nom")
    @Size(max = 40)
    private String nom;

    @NotNull
    @NotEmpty
    @Size(max = 40)
    @Column(name = "prenom")
    private String prenom;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @NotNull
    @NotEmpty
    @Column(name = "nationality")
    private String Nationality;

    //portefolio
    @Lob
    @Column(name = "portefolio", columnDefinition="BLOB")
    private byte[] portefolio;
    //CV
    @Lob
    @Column(name = "cv", columnDefinition="BLOB")
    private byte[] cv;

    @NotNull
    @NotEmpty
    @Column(name="yearsofExperience")
    private int Experience;
    @NotNull
    @NotEmpty
    @Column(name="ActualEntreprise")
    private String EntrepriseActuelle;

    @Column(name="motivationtext", columnDefinition="TEXT")
    private String motivationtext;

    //articles relation
    @OneToMany(mappedBy = "author")
    private Set<Article> articles ;
    //user favourite journalist
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "journalists")
    private Set<Internaute> internautes = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumtel() {
        return numtel;
    }

    public void setNumtel(long numtel) {
        this.numtel = numtel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public byte[] getPortefolio() {
        return portefolio;
    }

    public void setPortefolio(byte[] portefolio) {
        this.portefolio = portefolio;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }

    public String getEntrepriseActuelle() {
        return EntrepriseActuelle;
    }

    public void setEntrepriseActuelle(String entrepriseActuelle) {
        EntrepriseActuelle = entrepriseActuelle;
    }

    public String getMotivationtext() {
        return motivationtext;
    }

    public void setMotivationtext(String motivationtext) {
        this.motivationtext = motivationtext;
    }


}
