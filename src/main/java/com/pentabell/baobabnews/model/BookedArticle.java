package com.pentabell.baobabnews.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class BookedArticle implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @MapsId("ArticleID")
    private Article articles;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @MapsId("InternauteID")
    private Internaute Internautes;
//test lenovo
    @Column(name="DateSaved")
    @CreationTimestamp
    private LocalDateTime saved_date;

    public BookedArticle(Article articles, Internaute internautes, LocalDateTime saved_date) {
        this.articles = articles;
        Internautes = internautes;
        this.saved_date = saved_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookedArticle)) return false;
        BookedArticle that = (BookedArticle) o;
        return Objects.equals(articles, that.articles) &&
                Objects.equals(Internautes, that.Internautes) &&
                Objects.equals(saved_date, that.saved_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articles, Internautes, saved_date);
    }
}
