package com.pentabell.baobabnews.model.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pentabell.baobabnews.model.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Basic;
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
//    @JsonProperty("image")
//    private byte[] pic;

    @JsonProperty("content")
    private HashSet <ContentDetails>contentDetails;

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

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

//    public Language getLanguageArticle() {
//        return LanguageArticle;
//    }
//
//    public void setLanguageArticle(Language languageArticle) {
//        LanguageArticle = languageArticle;
//    }

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

//    public byte[] getPic() {
//        return pic;
//    }
//
//    public void setPic(byte[] pic) {
//        this.pic = pic;
//    }
//

    public HashSet<ContentDetails> getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(HashSet<ContentDetails> contentDetails) {
        this.contentDetails = contentDetails;
    }

//    public ContentDetails getContentDetails() {
//        return contentDetails;
//    }
//
//    public void setContentDetails(ContentDetails contentDetails) {
//        this.contentDetails = contentDetails;
//    }
//    public ContentDetailsForm getContentDetails() {
//        return contentDetails;
//    }
//
//    public void setContentDetails(ContentDetailsForm contentDetails) {
//        this.contentDetails = contentDetails;
//    }
}