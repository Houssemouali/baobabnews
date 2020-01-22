package com.pentabell.baobabnews.model;

import javax.persistence.*;

@Entity
public class Signalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;

    @ManyToOne
    @JoinColumn(name="ProblemId")
    private Problem problems;


    @ManyToOne
    @JoinColumn(name="user_id")
    private Users sig_user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
