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

    @Column(name = "UserId")
    private Long user_id;

    //@ManyToOne
    public Long getArticleID() {
        return articleID;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }
    //@ManyToOne
    public Long getInternauteId() {
        return user_id;
    }

    public void setInternauteId(Long user_id) {
        this.user_id = user_id;
    }

    public ArticleBookedID(Long articleID, Long user_id) {
        this.articleID = articleID;
        this.user_id = user_id;
    }

    public ArticleBookedID() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleID, user_id);
    }
}
