package com.pentabell.baobabnews.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class ASocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "Name",length = 20)
    private String Name;

    @NotNull
    @Column(name = "baseURL")
    private String baseURL;

    @OneToOne(mappedBy = "NameSocialMedia")
    private SocialMedia socialMedia;


}
