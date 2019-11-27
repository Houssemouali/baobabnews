package com.pentabell.baobabnews.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    @Column(name = "CategoryName")
//    private String CategoryName;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private CategoryName name;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "categories")
//    private Set<Article> articles = new HashSet<>();


    /*user's fav categorie refernce*/
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "categories")
//    private Set<Internaute> internautes = new HashSet<>();
//
//    /*country reference*/
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(name = "category_countries",
//            joinColumns = { @JoinColumn(name = "category_id") },
//            inverseJoinColumns = { @JoinColumn(name = "country_id") })
//    private Set<Country>countries  = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryName getName() {
        return name;
    }

    public Category() {
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    //public String getCategoryName() {
//        return CategoryName;
//    }

//    public void setCategoryName(String categoryName) {
//        CategoryName = categoryName;
//    }
//
//    public Category(@NotNull String categoryName) {
//        CategoryName = categoryName;
//    }
//
//    public Category() {
//    }
//
//    public Set<Article> getArticles() {
//        return articles;
//    }
//
//    public void setArticles(Set<Article> articles) {
//        this.articles = articles;
//    }
//
//    @Override
//    public String toString() {
//        return "Category{" +
//                "id=" + id +
//                ", CategoryName='" + CategoryName + '\'' +
//                '}';
//    }

}
