package com.pentabell.baobabnews.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "languageN")
    private String LanguageName;

    @OneToMany(mappedBy="LanguageArticle")
    private Set<Article>articles;

    public Language(Long id, @NotNull String languageName, Set<Article> articles) {
        this.id = id;
        LanguageName = languageName;
        this.articles = articles;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguageName() {
        return LanguageName;
    }

    public void setLanguageName(String languageName) {
        LanguageName = languageName;
    }
}
