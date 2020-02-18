package com.pentabell.baobabnews.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

import java.util.HashSet;

@Entity
@Table(name = "internaute")
//@NaturalIdCache
//@JsonIgnoreProperties
//@JsonFormat(shape= JsonFormat.Shape.ARRAY)
@DiscriminatorValue("1")
public class Internaute extends Users {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name="myseq",sequenceName="MY_SEQ",allocationSize = 1, initialValue= 1)
    @Column(name = "Internaute_ID",unique = true, nullable = false )
    //@PrimaryKeyJoinColumn
    private long id;

    //@NotNull
    //@NotEmpty(message = "Please enter number")
    //@Pattern(regexp = "(^$|[0-9]{10})")
//    @Column(name = "numtel")
//    private long numtel;

    //@NotNull
    //@NotEmpty
    //@NotEmpty(message = "Please enter language")    //@NotBlank
    @Column(name = "langue")
    private String langue;

    //@NotNull
    //@NotBlank
    //@NotEmpty
    @Column(name = "pays_suivi")
    private String pays_suivi;

    //@NotNull
    //@NotEmpty(message = "Please enter nationality")    //@NotBlank
    @Column(name = "nationality")
    private String nationality;




    //Categorie Reference
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(name = "User_FavCategories",
//            joinColumns = { @JoinColumn(name = "Internaute_ID") },
//            inverseJoinColumns = { @JoinColumn(name = "category_id") })
//    private Set<Category> categories = new HashSet<>();

    //sign a problem
//    @OneToMany(mappedBy ="internautes" )
//    private Set<Problem>problems;

    //Suivre pays
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "User_FavCountries",
            joinColumns = { @JoinColumn(name = "Internaute_ID") },
            inverseJoinColumns = { @JoinColumn(name = "country_id") })
    private Set<Country> countries = new HashSet<>();


    //follow journalist
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "Followed_Journalist",
            joinColumns = {@JoinColumn(name = "Internaute_ID")},
            inverseJoinColumns = {@JoinColumn(name = "journalist_id")})
    private Set<Journaliste> journalists = new HashSet<>();

    public Internaute(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @Size(min = 6, max = 100, message = "password must be between 6 and 20 character long") String password, @Pattern(regexp = "(^$|[0-9]{11})") String numtel, String nationality, Date dateNaissance, String langue, String pays_suivi) {
        super(email, username, password, numtel, nationality, dateNaissance);
        this.langue = langue;
        this.pays_suivi = pays_suivi;
        this.nationality = nationality;

    }
/*
    public Internaute(String email, String username, String password, String numtel, String langue, String pays_suivi, String nationality) {
        //super(email,username,password);
        //this.numtel = numtel;
        super(email, username, password, numtel, nationality, dateNaissance);
        this.langue = langue;
        this.pays_suivi = pays_suivi;
        this.nationality = nationality;
    }*/

    public Internaute(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @Size(min = 6, max = 100, message = "password must be between 6 and 20 character long") String password, @Pattern(regexp = "(^$|[0-9]{11})") String numtel, String nationality, Date dateNaissance) {
        super(email, username, password, numtel, nationality, dateNaissance);
    }

    public Internaute(String email, String username, String password) {
        super(email, username, password);
    }


//    public Internaute() {
//        super(email, username, password, numtel, nationality);
//    }
//
//    public Internaute(String email, String username, String encode, Date datenaiss) {
//    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public long getNumtel() {
//        return numtel;
//    }
//
//    public void setNumtel(long numtel) {
//        this.numtel = numtel;
//    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getPays_suivi() {
        return pays_suivi;
    }

    public void setPays_suivi(String pays_suivi) {
        this.pays_suivi = pays_suivi;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Internaute{" +
                "id=" + id +
              ", langue='" + langue + '\'' +
                ", pays_suivi='" + pays_suivi + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
