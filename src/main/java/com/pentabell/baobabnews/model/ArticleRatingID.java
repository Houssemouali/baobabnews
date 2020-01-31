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
    private Long User_id;


    @ManyToOne
    public Long getArticleID() {
        return articleID;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }

    @ManyToOne
    public Long getUser_id() {
        return User_id;
    }

    public void setUser_id(Long user_id) {
        User_id = user_id;
    }

    public ArticleRatingID(Long articleID, Long UserId) {
        this.articleID = articleID;
        this.User_id = UserId;
    }

    public ArticleRatingID() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleID, User_id);
    }
}
