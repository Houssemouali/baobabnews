package com.pentabell.baobabnews.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name="label")
    private String libelle;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade =
                    CascadeType.MERGE,

            mappedBy = "tags")
    private Set<Article> posts = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Tag(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }

    public Tag() {
    }

    public Tag(@NotNull @Size(max = 100) String libelle, Set<Article> posts) {
        this.libelle = libelle;
        this.posts = posts;
    }
}
