package com.pentabell.baobabnews.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.dom4j.tree.AbstractEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.Text;
import lombok.EqualsAndHashCode;

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

//    @Lob
//    @Column(name="image", columnDefinition="mediumblob")
//    private byte[] pic;


    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "dateCreated")
    private Date date = new Date();

    @Column(name="status")
    private String status="en cours";

    @Column(name="link")
    private String link;
    /*Tag reference*/
    @ManyToMany(fetch = FetchType.EAGER,
            cascade =
                    CascadeType.MERGE
    )
    @JoinTable(name = "article_tags",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags = new HashSet<>();

    /*Article language*/
//    @ManyToOne
//    @JoinColumn(name = "language_id")
//    //@JsonProperty("language")
//    //@JsonIgnore
//    private Language LanguageArticle;
    /*country reference*/
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "article_countries",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "country_id")})
    //@JsonProperty("country")
    private Set<Country> countries = new HashSet<>();

    //Content oneToOne Reference for article
//    @OneToOne(fetch = FetchType.LAZY,
//            cascade =  CascadeType.ALL ,
//            mappedBy = "article")

    //last version that work but without article id
//    @OneToMany(fetch=FetchType.LAZY,
//                cascade = CascadeType.ALL,
//                mappedBy = "article")
//    private Set<ContentDetails> contentDetails;
@OneToMany(cascade = CascadeType.ALL)
@JoinTable(name="Article_Content",
        joinColumns = @JoinColumn(name="Article_id"),
        inverseJoinColumns = @JoinColumn(name="Content_id")
)
private Set<ContentDetails> contentDetails;

    /*Category reference*/
    //@JsonProperty("category")
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL
//            ,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            }
    )
    @JoinTable(name = "article_categories",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<>();

    /*internaute reference to article bookmarks*/


    @OneToMany(mappedBy = "articles",
            orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonIgnore
    public Set<BookedArticle> internautes = new HashSet<>();

    //rating article
    @OneToMany(mappedBy = "articlesSet",
            cascade = javax.persistence.CascadeType.PERSIST,
            orphanRemoval = true)
    public Set<ArticleRating>articleRatings= new HashSet<>();

//(cascade = {CascadeType.ALL})( optional = true,cascade ={CascadeType.ALL})
//@JsonProperty("author")(cascade = CascadeType.ALL)
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "journalist_Id")
    //@JsonIgnore
    private Journaliste author;

    @OneToMany(mappedBy = "articless",
            cascade = {CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private Set<Image> images ;



    public Article() {
    }


//@NotNull String titre, @NotNull String content,, Language languageArticle
    public Article( @NotNull Date date, Tag tags, Country countries, Category categories, Journaliste author) {
        //this.titre = titre;
        //this.content = content;
        this.date = date;
        //LanguageArticle = languageArticle;
        this.author = author;
    }

    public Article(@NotNull String titre, @NotNull String content, @NotNull Date date, Language languageArticle, Journaliste author) {
        //this.titre = titre;
        //this.content = content;
        this.date = date;
        //LanguageArticle = languageArticle;
        this.author = author;
    }

    public Article(@NotNull String titre, @NotNull String content, @NotNull Date date, Language languageArticle, Set<Category> categories, Journaliste author,byte[] pic) {
        //this.titre = titre;
        //this.content = content;
        this.date = date;
        //LanguageArticle = languageArticle;
        this.categories = categories;
        this.author = author;
    }

    //
    public Article(@NotNull String titre, @NotNull String content, Date date) {
        //this.titre = titre;
        //this.content = content;
        this.date = date;
    }

    public Article(@NotNull String titre, String content,  Language languageArticle, Journaliste author, Set<Tag> tags,  Set<Country> countries) {
        //this.titre = titre;
        //this.content = content;
        //this.date = date;
        //LanguageArticle = languageArticle;
        this.author = author;
        this.tags = tags;
        //this.categories = categories;
        this.countries = countries;
    }
    public Article(@NotNull String titre, String content, Language languageArticle) {
        //this.titre = titre;
        //this.content = content;
        //LanguageArticle = languageArticle;
    }

    public Article(String title, ContentDetails contentDetails, Date date) {

    }

    public Article(Date date) {
    }

    public Article(Date date, String status) {

    }

/*    public Article(long idArticle, @NotNull Date date, String status, String link, Set<Tag> tags, Set<Country> countries, Set<ContentDetails> contentDetails, Set<Category> categories, Set<BookedArticle> internautes, Set<ArticleRating> articleRatings, Journaliste author) {
        IdArticle = idArticle;
        this.date = date;
        this.status = status;
        this.link = link;
        this.tags = tags;
        this.countries = countries;
        this.contentDetails = contentDetails;
        this.categories = categories;
        this.internautes = internautes;
        this.articleRatings = articleRatings;
        this.author = author;
    }*/

//    public Article(String titre, String content, Date date, Language languageArticle, Journaliste author, Set<Tag> tags, Set<Category> categories, Set<Country> countries) {
//    }
    //    public void addCategory(Category c){
//        this.getCategories().add(c);
//        //categories.add(c);
//        c.getArticles().add(this);
//    }

    public long getIdArticle() {
        return IdArticle;
    }

    public void setIdArticle(long idArticle) {
        IdArticle = idArticle;
    }

    public String getLink() {
        return link;
    }

    public Article(long idArticle, @NotNull Date date, String status, String link, Set<Tag> tags, Set<Country> countries, Set<ContentDetails> contentDetails, Set<Category> categories, Journaliste author) {
        IdArticle = idArticle;
        this.date = date;
        this.status = status;
        this.link = link;
        this.tags = tags;
        this.countries = countries;
        this.contentDetails = contentDetails;
        this.categories = categories;
        this.author = author;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    //
    public Journaliste getAuthor() {
        return author;
    }

    public void setAuthor(Journaliste author) {
        this.author = author;
    }

    //
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }


    public void setContentDetails(Set<ContentDetails> contentDetails) {
        this.contentDetails = contentDetails;
    }

    public Set<ContentDetails> getContentDetails() {
        return contentDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
