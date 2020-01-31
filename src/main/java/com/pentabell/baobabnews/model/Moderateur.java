package com.pentabell.baobabnews.model;

import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name="moderateur")
@NaturalIdCache
public class Moderateur extends Users {

    @Column(name="Moderateur_Id",unique = true, nullable = false)
    private int id;

//    @NotNull
//    @NotEmpty
    //@Pattern(regexp = "(^$|[0-9]{10})")

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Moderateur(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password) {
        super(email, username, password);
    }

    public Moderateur(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password, @NotNull @NotEmpty @Pattern(regexp = "(^$|[0-9]{10})") long numtel) {
        super(email, username, password);
    }

    public Moderateur(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @Size(min = 6, max = 100, message = "password must be between 6 and 20 character long") String password, @Pattern(regexp = "(^$|[0-9]{11})") String numtel, String nationality, Date dateNaissance) {
        super(email, username, password, numtel, nationality, dateNaissance);
    }

    public Moderateur() {
    }
}
