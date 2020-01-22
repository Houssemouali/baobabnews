package com.pentabell.baobabnews.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticleRatingID implements Serializable {

    @Column(name="ArticleID")
    private Long articleID;

    @Column(name = "UserId")
    private Long UserId;

    @ManyToOne
    public Long getArticleID() {
        return articleID;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }
    @ManyToOne
    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long UserId) {
        this.UserId = UserId;
    }

    public ArticleRatingID(Long articleID, Long UserId) {
        this.articleID = articleID;
        this.UserId = UserId;
    }

    public ArticleRatingID() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleID, UserId);
    }
}
