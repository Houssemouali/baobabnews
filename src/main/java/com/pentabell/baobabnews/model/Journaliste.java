package com.pentabell.baobabnews.model;

import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="journaliste")
@DiscriminatorValue("2")
@NaturalIdCache
public class Journaliste extends Users  {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Journalist_Id",unique = true, nullable = false)
    private long id;


//    private long postsNumber = 0;
//
//
//
//
//    public long getPostsNumber() {
//        return postsNumber;
//    }
//
//    public void setPostsNumber(long postsNumber) {
//        this.postsNumber = postsNumber;
//    }

    @Size(min = 2, max = 30)
    @Column(name = "name")
    private String name;

    @Size(min = 2, max = 30)
    @Column(name = "surname")
    private String surname;

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
    //@JsonIgnore
    @Column(name="ActualEntreprise")
    private String EntrepriseActuelle;

    @Column(name="motivationtext", columnDefinition="TEXT")
    private String motivationtext;


    @Column(name="status")
    private String status="en cours";

    //articles relation
    //,cascade = CascadeType.PERSIST
    @OneToMany(mappedBy = "author",
            cascade = {CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private Set<Article> articles ;
    //user favourite journalist
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "journalists")
    private Set<Internaute> internautes = new HashSet<>();


    //sign a problem
//    @OneToMany(mappedBy ="journalistes" )
//    private Set<Problem>problems;



//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }


//    public long getNumtel() {
//        return numtel;
//    }
//
//    public void setNumtel(long numtel) {
//        this.numtel = numtel;
//    }
//

//    public String getPrenom() {
//        return prenom;
//    }
//
//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }
//
//    public Date getDateNaissance() {
//        return dateNaissance;
//    }
//
//    public void setDateNaissance(Date dateNaissance) {
//        this.dateNaissance = dateNaissance;
//    }

//    public String getNationality() {
//        return Nationality;
//    }
//
//    public void setNationality(String nationality) {
//        Nationality = nationality;
//    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    /*ublic Journaliste(long numtel, @Size(max = 40) String name, @Size(max = 40) String surname, Date dateNaissance, String nationality, byte[] portefolio, byte[] cv, int experience, String entrepriseActuelle, String motivationtext, Set<Article> articles, Set<Internaute> internautes) {
//        this.id = id;
        //this.numtel = numtel;
   //     super(email, username, password, numtel, nationality);
        this.name = name;
     this.surname = surname;
        //this.dateNaissance = dateNaissance;
        //Nationality = nationality;
        this.portefolio = portefolio;
        this.cv = cv;
        Experience = experience;
        EntrepriseActuelle = entrepriseActuelle;
        this.motivationtext = motivationtext;
        this.articles = articles;
        this.internautes = internautes;
        this.internautes = internautes;
    }*/

/*
    public Journaliste(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password) {
        super(email, username, password);
    }*/

    public Journaliste() {
    }
/*

    public Journaliste(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @Size(min = 6, max = 100, message = "password must be between 6 and 20 character long") String password, @Pattern(regexp = "(^$|[0-9]{11})") String numtel, String nationality, Date dateNaissance) {
        super(email, username, password, numtel, nationality, dateNaissance);
    }
*/

    public Journaliste(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @Size(min = 6, max = 100, message = "password must be between 6 and 20 character long") String password, @Pattern(regexp = "(^$|[0-9]{11})") String numtel, String nationality, Date dateNaissance, int experience, String entrepriseActuelle, String motivationtext, String name, String surname, byte[] cv, byte[] portefolio, String status) {
        super(email, username, password, numtel, nationality, dateNaissance);

        this.Experience = experience;
        this.EntrepriseActuelle = entrepriseActuelle;
        this.motivationtext = motivationtext;
        this.name = name;
        this.surname = surname;
        this.cv = cv;
        this.portefolio = portefolio;
        this.status = status;
    }

    public Journaliste(String name, String status) {
        super();
        this.name = name;
        this.status = status;
    }


//    public Journaliste(String email, String username, String password, String actualEntreprise, String nationality, int experience, String nom, String prenom, String numtel, Date datenaiss, String motivationtext, byte[] cv, byte[] portefolio) {
//    }

    //,int experience
//    public Journaliste(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password,
//                       String entrepriseActuelle ,String nationality,int experience) {
//        super(email, username, password);
//        EntrepriseActuelle = entrepriseActuelle;
//        Nationality = nationality;
//        Experience = experience;
//
//    }
//public Journaliste(String email, String username, String encode, String actualEntreprise, String nationality, int experience, String nom, String prenom, int numtel, Date datenaiss, String motivationtext) {
//}

    /*public Journaliste(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password,
                       String entrepriseActuelle,String nationality, int experience, String name,
                        String surname, long numtel, Date dateNaissance,  String motivationtext, byte[] portefolio, byte[] cv) {
        super(email, username, password, numtel , nationality, dateNaissance);
        EntrepriseActuelle = entrepriseActuelle;
        Experience = experience;
     this.name = name;
      this.surname = surname;

        this.motivationtext = motivationtext;
        this.portefolio = portefolio;
        this.cv = cv;
    }


    public Journaliste(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password, byte[] portefolio, byte[] cv) {
        super(email, username, password);
        this.portefolio = portefolio;
        this.cv = cv;
    }*/

  /*  public Journaliste(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @Size(min = 6, max = 100, message = "password must be between 6 and 20 character long") String password, @Pattern(regexp = "(^$|[0-9]{11})") String numtel, String nationality, Date dateNaissance, long id, @Size(min = 2, max = 30) String name, @Size(min = 2, max = 30) String surname, int experience, String entrepriseActuelle, String motivationtext) {
        super(email, username, password, numtel, nationality, dateNaissance);
        this.id = id;
        this.name = name;
        this.surname = surname;
        Experience = experience;
        EntrepriseActuelle = entrepriseActuelle;
        this.motivationtext = motivationtext;
    }*/
}
