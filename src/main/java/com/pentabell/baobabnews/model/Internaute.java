package com.pentabell.baobabnews.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "internaute")
public class Internaute extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    /*internaute reference to article bookmarks*/
    @OneToMany(mappedBy = "internaute", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<BookedArticle> bookmarks = new HashSet<>();


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
