package com.pentabell.baobabnews.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 100)
    @Column(name="label",unique = true, nullable=false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {

                    CascadeType.MERGE
            },
            mappedBy = "countries")
    private Set<Article> articles = new HashSet<>();

    //category reference
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "countries")
//    private Set<Category> categories = new HashSet<>();

    //internaute followed countries
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "countries")
    private Set<Internaute> internautes = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country(@NotNull @Size(max = 100) String name, Set<Article> articles) {
        this.name = name;
        this.articles = articles;
    }

    public Country() {
    }
}
