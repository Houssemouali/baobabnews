package com.pentabell.baobabnews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "internaute")
@NaturalIdCache
public class Internaute extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Internaute_ID", unique = true, nullable = false)
    private int id;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})")
    @Column(name = "numtel")
    private long numtel;

    @NotNull
    @NotEmpty
    @Column(name = "langue")
    private String langue;

    @NotNull
    @NotEmpty
    @Column(name = "pays_suivi")
    private String pays_suivi;

    @NotNull
    @NotEmpty
    @Column(name = "nationality")
    private String nationality;

    //@JsonIgnore
    /*internaute reference to article bookmarks*/
    //@JsonIgnoreProperties("internautes")
//    @OneToMany(mappedBy = "internautePK",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private Set<BookedArticle> bookmarks = new HashSet<>();


    //Categorie Reference
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(name = "internaute_FavCategories",
//            joinColumns = { @JoinColumn(name = "Internaute_ID") },
//            inverseJoinColumns = { @JoinColumn(name = "category_id") })
//    private Set<Category> categories = new HashSet<>();


    public Internaute(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password, @NotNull @NotEmpty @Pattern(regexp = "(^$|[0-9]{10})") long numtel, @NotNull @NotEmpty String langue, @NotNull @NotEmpty String pays_suivi, @NotNull @NotEmpty String nationality) {
        super(email, username, password);
        this.numtel = numtel;
        this.langue = langue;
        this.pays_suivi = pays_suivi;
        this.nationality = nationality;
    }

    public Internaute() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Internaute{" +
                "id=" + id +
                ", numtel=" + numtel +
                ", langue='" + langue + '\'' +
                ", pays_suivi='" + pays_suivi + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
