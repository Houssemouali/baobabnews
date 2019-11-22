package com.pentabell.baobabnews.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="journaliste")
@NaturalIdCache
public class Journaliste  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Journalist_Id",unique = true, nullable = false)
    private Long id;

//    //@NotBlank
//    @Size(max = 50)
//    @Email
//    @Column(name = "email")
//    private String email;
//
//    //@NotBlank
//    @Size(min=3, max = 50)
//    @Column(name = "username")
//    private String username;
//
//    //@JsonIgnore
//    //@NotBlank
//    @Size(min=6, max = 20,message="password must be between 6 and 20 character long")
//    @Column(name = "password")
//    private String password;

    //@NotNull
    //@NotEmpty
    //@Pattern(regexp = "(^$|[0-9]{10})")
    @Column(name = "numtel")
    private long numtel;

    //@NotNull
    //@NotEmpty
    @Column(name = "nom")
    @Size(max = 40)
    private String nom;

    //@NotNull
    //@NotEmpty
    @Size(max = 40)
    @Column(name = "prenom")
    private String prenom;

    @JsonFormat(pattern="yyyy-MM-dd")
    //@Temporal(TemporalType.DATE)
    private Date dateNaissance;

    //@NotNull
    //@NotEmpty
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

    //@NotNull
    //@NotEmpty
    @Column(name="yearsofExperience")
    private int Experience;

    //@NotNull
    //@NotEmpty
    @JsonIgnore
    @Column(name="ActualEntreprise")
    private String EntrepriseActuelle;

    @Column(name="motivationtext", columnDefinition="TEXT")
    private String motivationtext;

    //articles relation
    //,cascade = CascadeType.PERSIST
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    //    public Journaliste(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password, Long id, Set<Article> articles) {
//        super(email, username, password);
//        this.id = id;
//        this.articles = articles;
//    }

    public Journaliste(Long id, long numtel, @Size(max = 40) String nom, @Size(max = 40) String prenom, Date dateNaissance, String nationality, byte[] portefolio, byte[] cv, int experience, String entrepriseActuelle, String motivationtext, Set<Article> articles, Set<Internaute> internautes) {
        this.id = id;
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        Nationality = nationality;
        this.portefolio = portefolio;
        this.cv = cv;
        Experience = experience;
        EntrepriseActuelle = entrepriseActuelle;
        this.motivationtext = motivationtext;
        this.articles = articles;
        this.internautes = internautes;
    }

    public Journaliste() {
    }
}
