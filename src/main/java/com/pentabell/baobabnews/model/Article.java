package com.pentabell.baobabnews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dom4j.tree.AbstractEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.Text;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "article")
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "Article_ID", unique = true, nullable = false)
    private long IdArticle;

    @NotNull
    @Column(name = "title")
    private String titre;

    @NotNull
    @Column(name = "content")
    @Lob
    private String content;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name="dateCreated")
    private Date date=new Date();

    /*Tag reference*/
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "article_tags",
            joinColumns = { @JoinColumn(name = "article_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<>();

    /*Article language*/
    @ManyToOne
    @JoinColumn(name="language_id",nullable=false)
    private Language LanguageArticle;
    /*country reference*/
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "article_countries",
            joinColumns = { @JoinColumn(name = "article_id") },
            inverseJoinColumns = { @JoinColumn(name = "country_id") })
    private Set<Country>countries  = new HashSet<>();

    /*Category reference*/
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "article_categories",
            joinColumns = { @JoinColumn(name = "article_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private Set<Category>categories  = new HashSet<>();

    /*internaute reference to article bookmarks*/


   @OneToMany(mappedBy = "articles",
           orphanRemoval = true, cascade=CascadeType.PERSIST)
   public Set<BookedArticle> internautes  = new HashSet<>();

    @ManyToOne
    @JoinColumn (name="journalist_Id",nullable=false)
    private Journaliste author;



    public Article(@NotNull String titre, @NotNull String content, Date date) {
        this.titre = titre;
        this.content = content;
        this.date = date;
    }

    public long getIdArticle() {
        return IdArticle;
    }

    public void setIdArticle(long idArticle) {
        IdArticle = idArticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
