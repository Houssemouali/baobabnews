package com.pentabell.baobabnews.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Content_details")
public class ContentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "content_text")
    @Lob
    private String content;

    @NotNull
    @Column(name = "title")
    private String titre;

//    @OneToOne(fetch = FetchType.LAZY,
//            optional = false,
//            cascade = CascadeType.ALL)
//    @JoinColumn(name = "article_id", nullable = false)
//
    //working version with article id inside table content
//    @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
//    @JoinColumn(name="article_id")
//    private Article article;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="Article_Content",
            joinColumns = @JoinColumn(name="content_id"),
            inverseJoinColumns  =@JoinColumn(name="article_id"))
    private Article article;
//    @OneToOne(fetch = FetchType.LAZY,optional = false )
//    @JoinColumn(name="language_id",nullable=false,unique = false)
//
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name="language_id")
    private Language languageArticle;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Language getLanguageArticle() {
        return languageArticle;
    }

    public void setLanguageArticle(Language languageArticle) {
        this.languageArticle = languageArticle;
    }

//    public Long getArticle() {
//        return article.getIdArticle();
//    }
//    public void setArticle(Article article) {
//        this.article = article;
//    }
}
