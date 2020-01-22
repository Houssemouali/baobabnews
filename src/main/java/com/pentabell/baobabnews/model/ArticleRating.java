package com.pentabell.baobabnews.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Article_Rating")
public class ArticleRating {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long IdRating;

    @EmbeddedId
    private ArticleRatingID idRating=new ArticleRatingID();

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            targetEntity=Article.class)
//    @JoinColumn(
//      name = "IdArticle", insertable = false, updatable = false)
    @MapsId("ArticleID")
    Set<Article> articles;


    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity=Users.class,
            cascade = CascadeType.PERSIST)
    @MapsId("UserId")
//    @JoinColumn(name = "id", insertable = false, updatable = false)
//   @MapsId("InternauteID")
    Set<Users> users_rating;


    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "dateRated")
    private Date date = new Date();


    @NotNull
    @Column(name="RateNumber")
    @Digits(integer=5, fraction=2)
    private double rateNumber;

//    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
//    @JoinColumn(name="ArticleId")
//    private Article article;
//
//    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL)
//    @JoinColumn(name="user_id")
//    private Set<Internaute> users;

}
