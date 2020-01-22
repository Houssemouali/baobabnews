package com.pentabell.baobabnews.model;

import javax.persistence.*;
@Entity
public class SocialMedia {
    @Id
    private Long Id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="NameSocialMedia",referencedColumnName = "name")
    private ASocialMedia NameSocialMedia;
    //foreign key the id user

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "IdUser")
    private Users idUser;

    @Column(name="username")
    private String username;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
