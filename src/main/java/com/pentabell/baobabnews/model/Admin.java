package com.pentabell.baobabnews.model;

import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="admin")
@NaturalIdCache
public class Admin extends Users {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="admin_Id",unique = true, nullable = false)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Admin(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @Size(min = 6, max = 100, message = "password must be between 6 and 20 character long") String password, @Pattern(regexp = "(^$|[0-9]{11})") String numtel, String nationality, Date dateNaissance) {
        super(email, username, password, numtel, nationality, dateNaissance);
    }
}
