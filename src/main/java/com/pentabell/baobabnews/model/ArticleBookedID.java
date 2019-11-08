package com.pentabell.baobabnews.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

//@Embeddable
public class ArticleBookedID implements Serializable {
    @Column(name="ArticleID")
    private Article articleID;

    @Column(name = "internaute_id")
    private Internaute internauteId;

    @ManyToOne
    public Article getArticleID() {
        return articleID;
    }

    public void setArticleID(Article articleID) {
        this.articleID = articleID;
    }
    @ManyToOne
    public Internaute getInternauteId() {
        return internauteId;
    }

    public void setInternauteId(Internaute internauteId) {
        this.internauteId = internauteId;
    }

    public ArticleBookedID(Article articleID, Internaute internauteId) {
        this.articleID = articleID;
        this.internauteId = internauteId;
    }

    public ArticleBookedID() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleID, internauteId);
    }
}
