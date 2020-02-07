package com.pentabell.baobabnews.model.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pentabell.baobabnews.model.Language;
import org.hibernate.annotations.Type;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ContentDetailsForm {

    @NotBlank
    @Size(min = 5, max = 100)
    private String title;

    @NotBlank
    @Type(type="text")
    private String Content;

    @JsonProperty("language")
    private Language LanguageArticle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Language getLanguageArticle() {
        return LanguageArticle;
    }

    public void setLanguageArticle(Language languageArticle) {
        LanguageArticle = languageArticle;
    }
}
