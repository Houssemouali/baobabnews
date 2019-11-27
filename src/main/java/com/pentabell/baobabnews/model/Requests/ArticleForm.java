package com.pentabell.baobabnews.model.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pentabell.baobabnews.model.Country;
import com.pentabell.baobabnews.model.Journaliste;
import com.pentabell.baobabnews.model.Language;
import com.pentabell.baobabnews.model.Tag;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ArticleForm {
    @NotBlank
    @Size(min = 3, max = 80)
    private String title;

    @NotBlank
    @Lob
    private String content;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date date = new Date();

    private Set<String> category;

    @JsonProperty("language")
    private Language LanguageArticle;

    private Journaliste author;

    @JsonProperty("tags")
    private HashSet<Tag> tags;

    @JsonProperty("country")
    private HashSet<Country> countries;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Set<String> getCategory() {
        return category;
    }

    public void setCategory(Set<String> category) {
        this.category = category;
    }

    public Language getLanguageArticle() {
        return LanguageArticle;
    }

    public void setLanguageArticle(Language languageArticle) {
        LanguageArticle = languageArticle;
    }

    public Journaliste getAuthor() {
        return author;
    }

    public void setAuthor(Journaliste author) {
        this.author = author;
    }

    public HashSet<Tag> getTags() {
        return tags;
    }

    public void setTags(HashSet<Tag> tags) {
        this.tags = tags;
    }

    public HashSet<Country> getCountries() {
        return countries;
    }

    public void setCountries(HashSet<Country> countries) {
        this.countries = countries;
    }
}
