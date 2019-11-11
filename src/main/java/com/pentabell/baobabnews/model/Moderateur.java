package com.pentabell.baobabnews.model;

import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="moderateur")
@NaturalIdCache
public class Moderateur extends Users {

    @Column(name="Moderateur_Id",unique = true, nullable = false)
    private int id;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})")
    @Column(name = "numtel")
    private long numtel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumtel() {
        return numtel;
    }

    public void setNumtel(long numtel) {
        this.numtel = numtel;
    }

}
