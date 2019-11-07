package com.pentabell.baobabnews.model;

import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name = "internaute")
public class Internaute extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{10})")
    private long numtel;

    @NotNull
    @NotEmpty
    private String langue;
    @NotNull
    @NotEmpty
    private String pays_suivi;
    @NotNull
    @NotEmpty
    private String nationality;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Internaute(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password, @NotNull @NotEmpty @Pattern(regexp = "(^$|[0-9]{10})") long numtel, @NotNull @NotEmpty String langue, @NotNull @NotEmpty String pays_suivi, @NotNull @NotEmpty String nationality) {
        super(email, username, password);
        this.numtel = numtel;
        this.langue = langue;
        this.pays_suivi = pays_suivi;
        this.nationality = nationality;
    }

    public Internaute() {
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
