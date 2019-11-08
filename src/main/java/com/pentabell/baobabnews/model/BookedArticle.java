package com.pentabell.baobabnews.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


//@Entity
//@Table(name="BookedArticle")
//@AssociationOverrides({
//        @AssociationOverride(name = "articlePK",
//                joinColumns = @JoinColumn(name = "Article_ID")),
//        @AssociationOverride(name = "internautePK",
//                joinColumns = @JoinColumn(name = "Internaute_ID")) })
public class BookedArticle implements Serializable  {
    @EmbeddedId
    private ArticleBookedID idbook=new ArticleBookedID();


//    @ManyToOne(fetch = FetchType.LAZY,
//            targetEntity=Article.class)
//    @JoinColumn(
//      name = "IdArticle", insertable = false, updatable = false)
//    @MapsId("ArticleID")
//    Set<Article> articles;
//
//
//    @ManyToOne(fetch = FetchType.LAZY,
//            targetEntity=Internaute.class)
//    @JoinColumn(name = "id", insertable = false, updatable = false)
//   @MapsId("InternauteID")
//    Set<Internaute> internautes;

//test lenovo
    @Column(name="DateSaved")
    @CreationTimestamp
    private LocalDateTime saved_date;



    public ArticleBookedID getIdbook() {
        return idbook;
    }

    public void setIdbook(ArticleBookedID idbook) {
        this.idbook = idbook;
    }
    @Transient
    public Article getArticle(Article article){
        return getIdbook().getArticleID();
    }

    public void setArticle(Article article){
        getIdbook().setArticleID(article);
    }
    @Transient
    public Internaute getInternaute(Internaute internaute){
        return getIdbook().getInternauteId();
    }
    public void setInternaute(Internaute internaute){
        getIdbook().setInternauteId(internaute);
    }

//
//    public Set<Article> getArticles() {
//        return articles;
//    }
//
//    public void setArticles(Set<Article> articles) {
//        this.articles = articles;
//    }
//
//    public Set<Internaute> getInternautes() {
//        return internautes;
//    }
//
//    public void setInternautes(Set<Internaute> internautes) {
//        this.internautes = internautes;
//    }
//
//    public LocalDateTime getSaved_date() {
//        return saved_date;
//    }
//
//    public void setSaved_date(LocalDateTime saved_date) {
//        this.saved_date = saved_date;
//    }
//
//    public BookedArticle(Set<Article> articles, Set<Internaute> internautes, LocalDateTime saved_date) {
//        this.articles = articles;
//        this.internautes = internautes;
//       // this.saved_date = saved_date;
//
//    }

//    public BookedArticle(Article articles, Internaute internautes, LocalDateTime saved_date) {
//        this.articles = articles;
//        internautes = internautes;
//        this.saved_date = saved_date;
//        this.idbook=new ArticleBookedID(articles.getIdArticle(),internautes.getIdUser());
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof BookedArticle)) return false;
//        BookedArticle that = (BookedArticle) o;
//        return Objects.equals(articles, that.articles) &&
//                Objects.equals(internautes, that.internautes)&&
//                Objects.equals(saved_date, that.saved_date);
//
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(articles, internautes,saved_date);
//        //,saved_date
//    }
}
