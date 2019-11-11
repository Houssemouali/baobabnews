package com.pentabell.baobabnews.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticleBookedID implements Serializable {
    @Column(name="ArticleID")
    private Long articleID;

    @Column(name = "InternauteId")
    private Long internauteId;

    @ManyToOne
    public Long getArticleID() {
        return articleID;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }
    @ManyToOne
    public Long getInternauteId() {
        return internauteId;
    }

    public void setInternauteId(Long internauteId) {
        this.internauteId = internauteId;
    }

    public ArticleBookedID(Long articleID, Long internauteId) {
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
